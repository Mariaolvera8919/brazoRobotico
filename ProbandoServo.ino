
 
// Incluímos la librería para poder controlar el servo
#include <Servo.h>
 
// Declaramos la variable para controlar el servo
Servo servoMotor;
 
void setup() {
  // Iniciamos el monitor serie para mostrar el resultado
  Serial.begin(9600);
 
  // Iniciamos el servo para que empiece a trabajar con el pin 9
  servoMotor.attach(9);

  // Inicializamos al ángulo 0 el servomotor
  servoMotor.write(0);
}
 
void loop() {
  for(int i = 0; i <= 100; i += 20) {
    servoMotor.write(i);
    delay(1000);

    Serial.println(servoMotor.read());
  }

  for(int i = 100; i >= 0; i -= 20) {
    servoMotor.write(i);
    delay(1000);

    Serial.println(servoMotor.read());
  }

  /*servoMotor.write(0);
  delay(1000);
  
  servoMotor.write(90);
  delay(1000);

  servoMotor.write(180);
  delay(1000);

  servoMotor.write(270);
  delay(1000);

  servoMotor.write(360);
  delay(1000);*/
}
