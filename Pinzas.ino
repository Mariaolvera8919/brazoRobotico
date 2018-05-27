/*
PROGRAMA QUE PERMITE MANTENER COMUNICACIÓN ENTRE ARDUINO Y JAVA
A TRAVÉS DE LA COMUNICACIÓN SERIAL.
JAVA ENVÍA MENSAJES PARA MOVER LAS PINZAS DEL BRAZO ROBÓTICO, MIENTRAS
QUE EL ARDUINO ENVÍA MENSAJES A JAVA PARA CUANDO OCURRE UNA INTERRUPCIÓN.
*/

// INCLUIR LA LIBRERÍA PARA PODER CONTROLAR EL SERVO COM3
#include <Servo.h>

Servo pinzas;                 // SERVOMOTOR
int movimientoPinzas = 100;   // VARIABLE PARA GRADOS DE LAS PINZAS
int input;                    // ALMACENA MENSAJE DE JAVA
int PULSADOR = 2;             // PIN PARA BOTÓN
int LED = 13;                 // PIN PARA LED
boolean estado = true;        // BANDERA QUE CAMBIA CUANDO SUCEDE LA INTERRUPCIÓN

void setup() {
  Serial.begin(9600);         // INICIAR LA COMUNICACIÓN SERIAL
  pinMode(PULSADOR, INPUT);   // ESTABLECER EL BOTÓN COMO ENTRADA
  pinMode(LED, OUTPUT);       // ESTABLECER EL LED COMO SALIDA
  attachInterrupt(digitalPinToInterrupt(PULSADOR), blink, CHANGE); // DECLARAR INTERRUPCIÓN
  
  pinzas.attach(9);           // ENLAZAR EL PIN 9 AL SERVOMOTOR
  pinzas.write(100);          // INICIALIZAR LAS PINZAS CERRADAS
}

void loop() {
  if (digitalRead(PULSADOR) == LOW) { // SI SE PRESIONÓ EL BOTÓN
    digitalWrite(LED, HIGH);          // ENCENDER EL LED
  } else {                            // SI NO SE HA PRESIONADO
    digitalWrite(LED, LOW);           // APAGAR EL LED

    while (Serial.available() > 0) {  // SI HAY UN MENSAJE DISPONIBLE
      input = Serial.read();          // GUARDAR EN LA VARIABLE EL MENSAJE
      moverPinzas(input);             // LLAMAR AL MÉTODO QUE MUEVE LAS PINZAS
    }
  }
}

/*MÉTODO QUE PERMITE MOVER LAS PINZAS EN FUNCIÓN DE LOS GRADOS DEL PARÁMETRO*/
void moverPinzas(int input) {
  if (input == '1') {                   // ABRIR PINZAS
    if (movimientoPinzas >= 30)         // VALIDAR QUE NO CIERRE EN UN GRADO NO PERMITIDO
      movimientoPinzas -= 10;           // RESTAR 10 GRADOS
  } else if (input == '0') {            // CERRAR PINZAS
    if (movimientoPinzas <= 100)        // VALIDAR QUE NO ABRA EN UN GRADO NO PERMITIDO
      movimientoPinzas += 10;           // SUMAR 10 GRADOS
  }

  if (input == '1' || input == '0') {   // SI EL MENSAJE ES 1 O 0
    pinzas.write(movimientoPinzas);     // ABRIR EN LOS GRADOS QUE SE ENCUENTRAN EN LA VARIABLE
  } else if (input == '2') {            // SI EL MENSAJE ES 2
    pinzas.write(100);                  // CERRAR COMPLETAMENTE
    delay(500);                         // RETARDO DE MEDIO SEGUNDO
  } else if (input == '3') {            // SI EL MENSAJE ES 3
    pinzas.write(30);                   // ABRIR COMPLETAMENTE
    delay(500);                         // RETARDO DE MEDIO SEGUNDO
  }
}

/*MÉTODO AL QUE SE LLAMA CUANDO SUCEDE LA INTERRUPCIÓN*/
void blink() {
  estado = !estado;       // NEGAR EL ESTADO
  Serial.println(estado); // ENVIAR SU VALOR A JAVA
}
