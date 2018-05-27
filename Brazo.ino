/*
PROGRAMA QUE PERMITE MANTENER COMUNICACIÓN ENTRE ARDUINO Y JAVA
A TRAVÉS DE LA COMUNICACIÓN SERIAL.
JAVA ENVÍA MENSAJES PARA MOVER EL BRAZO DEL BRAZO ROBÓTICO, MIENTRAS
QUE EL ARDUINO ENVÍA MENSAJES A JAVA PARA CUANDO OCURRE UNA INTERRUPCIÓN.
*/

// INCLUIR LA LIBRERÍA PARA PODER CONTROLAR EL SERVO
#include <Servo.h>

Servo brazo;                // SERVOMOTOR
int movimientoBrazo = 90;   // VARIABLE PARA GRADOS DEL BRAZO
int input;                  // ALMACENA MENSAJE DE JAVA
int PULSADOR = 2;           // PIN PARA BOTÓN QUE CONTROLA INTERRUPCIÓN
int LED = 13;               // PIN PARA LED
boolean estado = true;      // BANDERA QUE CAMBIA CUANDO SUCEDE LA INTERRUPCIÓN

void setup() {
  Serial.begin(9600);       // INICIAR LA COMUNICACIÓN SERIAL
  pinMode(PULSADOR, INPUT); // ESTABLECER EL BOTÓN COMO ENTRADA
  pinMode(LED, OUTPUT);     // ESTABLECER EL LED COMO SALIDA
  attachInterrupt(digitalPinToInterrupt(PULSADOR), blink, CHANGE); // DECLARAR INTERRUPCIÓN

  brazo.attach(9);          // ENLAZAR EL PIN 9 AL SERVOMOTOR
  brazo.write(30);          // INICIALIZAR LAS EL BRAZO EN 30
}

void loop() {
  if (digitalRead(PULSADOR) == LOW) { // SI SE PRESIONÓ EL BOTÓN
    digitalWrite(LED, HIGH);          // ENCENDER EL LED
  } else {
    digitalWrite(LED, LOW);           // APAGAR EL LED

    while (Serial.available() > 0) {  // SI HAY UN MENSAJE DISPONIBLE
      input = Serial.read();          // GUARDAR EL MENSAJE EN VARIABLE
      moverBrazo(input);              // LLAMAR MÉTODO PARA MOVER BRAZO
    }
  }
}

/*MÉTODO QUE PERMITE MOVER EL BRAZO EN FUNCIÓN DE LOS GRADOS DEL PARÁMETRO*/
void moverBrazo(int input) {
  if (input == '0') {                 // SI JAVA ENVÍA UN 0
    if (movimientoBrazo >= 30)        // SI LOS GRADOS DE LA VARIABLE ES MAYOR A 30
      movimientoBrazo -= 10;          // RESTAR A LA VARIABLE 10 GRADOS PARA BAJAR BRAZO
  } else if (input == '1') {          // SI JAVA ENVÍA UN 1
    if (movimientoBrazo <= 90)        // SI LOS GRADOS DE LA VARIABLE SON MENORES A 90
      movimientoBrazo += 10;          // SUMAR A LA VARIABLE PARA SUBIR BRAZO
  }

  if (input == '1' || input == '0') {   // SI EL MENSAJE ES 1 O 0
    brazo.write(movimientoBrazo);       // ABRIR EN LOS GRADOS QUE SE ENCUENTRAN EN LA VARIABLE
  } else if (input == '2') {            // SI EL MENSAJE ES 2
    brazo.write(30);                    // BAJAR COMPLETAMENTE
    delay(500);                         // RETARDO DE MEDIO SEGUNDO
  } else if (input == '3') {            // SI EL MENSAJE ES 3
    brazo.write(100);                   // LEVANTAR COMPLETAMENTE
    delay(500);                         // RETARDO DE MEDIO SEGUNDO
  }
}

/*MÉTODO AL QUE SE LLAMA CUANDO SUCEDE LA INTERRUPCIÓN*/
void blink() {
  estado = !estado;       // NEGAR EL ESTADO
  Serial.println(estado); // ENVIAR SU VALOR A JAVA
}
