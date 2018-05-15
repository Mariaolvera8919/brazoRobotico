 /*
  Creado: Luis del Valle (ldelvalleh@programarfacil.com)
  https://programarfacil.com
*/
 
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
  Serial.println(servoMotor.read());
  delay(1000);
  
  //Desplazamos a la posición 0º
  servoMotor.write(180);
  // Esperamos 1 segundo
  delay(1000);

  Serial.println(servoMotor.read());
  delay(1000);
  
  // Desplazamos a la posición 90º
  servoMotor.write(0);
  // Esperamos 1 segundo
  delay(1000);

  Serial.println(servoMotor.read());
  delay(1000);
}
