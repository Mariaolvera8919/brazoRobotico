// Incluímos la librería para poder controlar el servo
#include <Servo.h>

// Declaramos la variable para controlar el servo
Servo codo;
int movimientoCodo = 90;
int input;
 
void setup() {
  // Iniciamos el monitor serie para mostrar el resultado
  Serial.begin(9600);
 
  // Iniciamos el servo para que empiece a trabajar con el pin 9
  codo.attach(9);

  // Inicializamos el codo en 90°
  codo.write(90);
}
 
void loop() {
  while (Serial.available() > 0) { // SI HAY UN MENSAJE DISPONIBLE
    input = Serial.read();

    if(input == '0') { // LEVANTAR CODO
      if(movimientoCodo >= 30)
        movimientoCodo -= 10;
    } else if(input == '1') { // BAJAR CODO
      if(movimientoCodo <= 140)
        movimientoCodo += 10;
    }

    codo.write(movimientoCodo);
    Serial.println(Serial.read());
    //delay(700);
  }
}

