// Incluímos la librería para poder controlar el servo
#include <Servo.h>

// Declaramos la variable para controlar el servo
Servo brazo;
int movimientoBrazo = 90;
int input;
 
void setup() {
  // Iniciamos el monitor serie para mostrar el resultado
  Serial.begin(9600);
 
  // Iniciamos el servo para que empiece a trabajar con el pin 9
  brazo.attach(9);

  // Inicializamos el brazo en 90°
  brazo.write(30);
}
 
void loop() {
  while (Serial.available() > 0) { // SI HAY UN MENSAJE DISPONIBLE
    input = Serial.read();

    if(input == '0') { // LEVANTAR brazo
      if(movimientoBrazo >= 30)
        movimientoBrazo -= 10;
    } else if(input == '1') { // BAJAR brazo
      if(movimientoBrazo <= 90)
        movimientoBrazo += 10;
    }

    brazo.write(movimientoBrazo);
    Serial.println(Serial.read());
    //delay(700);
  }
}

