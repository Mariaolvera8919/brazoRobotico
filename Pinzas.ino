// Incluímos la librería para poder controlar el servo
#include <Servo.h>

// Declaramos la variable para controlar el servo
Servo pinzas;
int movimientoPinzas = 100;
int input;
 
void setup() {
  // Iniciamos el monitor serie para mostrar el resultado
  Serial.begin(9600);
 
  // Iniciamos el servo para que empiece a trabajar con el pin 9
  pinzas.attach(9);

  // Inicializamos las pinzas cerradas
  pinzas.write(80);
}
 
void loop() {
  while (Serial.available() > 0) { // SI HAY UN MENSAJE DISPONIBLE
    input = Serial.read();

    if(input == '1') { // ABRIR PINZAS
      if(movimientoPinzas >= 30)
        movimientoPinzas -= 10;
    } else if(input == '0') { // CERRAR PINZAS
      if(movimientoPinzas <= 100)
        movimientoPinzas += 10;
    }

    pinzas.write(movimientoPinzas);
    Serial.println(Serial.read());
    //delay(700);
  }
}
