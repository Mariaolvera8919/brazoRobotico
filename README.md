PROYECTO BRAZO ROBÓTICO CONTROLADO POR PROGRAMA EN JAVA

INTEGRANTES:
-Hugo Rodrigo Murillo Martínez
-María de Jesús Olvera Rivera
-Cinthya Cassandra Rangel

INTRODUCCIÓN

Arduino es una plataforma de prototipos electrónicos, esta placa fue diseñada para facilitar el uso de la electrónica en proyectos multidisciplinarios, se pueden trabajar con otros componentes electrónicos como los servo motores, en el siguiente proyecto se implementará un brazo robótico el cual se podrá manipular a través de Arduino y una interfaz Java.

DESCRIPCIÓN

Este brazo robótico se estará hecho de material de acrílico el cual contendrá 4 servo motores conectados a placas Arduino, estas placas manipularán los movimientos del brazo, utilizando las plataformas de Java y Arduino.

OBJETIVO

Armar un brazo robótico el cual se pueda manipular a través de una interfaz conectando las plataformas Arduino y Java.

MATERIAL:

-Brazo Robótico
-4 servo motores
-4 placas Arduino
-Cables

ARMADO DEL BRAZO ROBÓTICO

Para comenzar se tiene que armar el brazo robótico, en la imágenes siguientes se describirá el proceso:

1.- Se arma la el hombro del robot y se pone uno de los servos.
![alt text](https://github.com/Mariaolvera8919/brazoRobotico/blob/master/WhatsApp%20Image%202018-05-14%20at%204.52.01%20PM.jpeg)

2.- Ahora se arma el codo del robot, al igual que el hombro, ponemos el servo en medio de las piezas. Una vez armado el codo y hombro se unen con dos piezas largas.
![alt text](https://github.com/Mariaolvera8919/brazoRobotico/blob/master/WhatsApp%20Image%202018-05-14%20at%204.52.02%20PM.jpeg)

3.- Una vez terminado  el paso anterior, se prosigue a armar las pinzas del robot.
![alt text](https://github.com/Mariaolvera8919/brazoRobotico/blob/master/WhatsApp%20Image%202018-05-14%20at%204.51.58%20PM.jpeg)

4.- Después agregamos las pinzas a al resto del brazo:
![alt text](https://github.com/Mariaolvera8919/brazoRobotico/blob/master/WhatsApp%20Image%202018-05-14%20at%204.51.55%20PM.jpeg)

5.- Por ultimo agregamos la base al  brazo robot.
![alt text](https://github.com/Mariaolvera8919/brazoRobotico/blob/master/WhatsApp%20Image%202018-05-16%20at%207.38.58%20PM.jpeg)

PROGRAMA EN JAVA Y PRUEBA DEL USO DEL SOFTWARE LIBRE

A continuación se muestran las fotos del programa en Java que se realizó para controlar el brazo, y el uso de Linux para la realización del mismo.

Programa en Java.
![alt text](https://github.com/Mariaolvera8919/brazoRobotico/blob/master/3.png)
![alt text](https://github.com/Mariaolvera8919/brazoRobotico/blob/master/2.png)

Programa en Arduino.
![alt text](https://github.com/Mariaolvera8919/brazoRobotico/blob/master/4.png)

Circuitos para presentación final en clase:
![alt text](https://github.com/Mariaolvera8919/brazoRobotico/blob/master/WhatsApp%20Image%202018-05-18%20at%205.01.43%20PM.jpeg)

Presionando botón que lanza interrupción y muestra de que el LED se enciende cuando sucede:

Antes de la interrupción:
![alt text](https://github.com/Mariaolvera8919/brazoRobotico/blob/master/WhatsApp%20Image%202018-05-18%20at%205.01.45%20PM.jpeg)
Después de la interrupción:
![alt text](https://github.com/Mariaolvera8919/brazoRobotico/blob/master/WhatsApp%20Image%202018-05-18%20at%205.01.44%20PM.jpeg)

Primeras pruebas del brazo funcionando:
![alt text](https://github.com/Mariaolvera8919/brazoRobotico/blob/master/WhatsApp%20Video%202018-05-16%20at%209.03.34%20PM.mp4)

Presentación en clase del funcionamiento completo del proyecto, mostrando el programa y el brazo funcionando:
![alt text](https://github.com/Mariaolvera8919/brazoRobotico/blob/master/WhatsApp%20Video%202018-05-18%20at%205.01.43%20PM.mp4)

Presentación en clase del funcionamiento de la interrupción en el programa y su reacción:
![alt text](https://github.com/Mariaolvera8919/brazoRobotico/blob/master/WhatsApp%20Video%202018-05-18%20at%205.01.44%20PM.mp4)


Los archivos de los programas de Arduino son los siguientes:

-Pinzas: https://github.com/Mariaolvera8919/brazoRobotico/blob/master/Pinzas.ino

-Codo: https://github.com/Mariaolvera8919/brazoRobotico/blob/master/Codo.ino

-Brazo: https://github.com/Mariaolvera8919/brazoRobotico/blob/master/Brazo.ino

-Base: https://github.com/Mariaolvera8919/brazoRobotico/blob/master/Base.ino

Los archivos de los programas en Java son los siguientes:

-TestBrazo: https://github.com/Mariaolvera8919/brazoRobotico/blob/master/TestBrazo.java

-Contenedora: https://github.com/Mariaolvera8919/brazoRobotico/blob/master/Contenedora.java

-Control: https://github.com/Mariaolvera8919/brazoRobotico/blob/master/Control.java

-Registro: https://github.com/Mariaolvera8919/brazoRobotico/blob/master/Registro.java

-EjecutarPasos: https://github.com/Mariaolvera8919/brazoRobotico/blob/master/EjecutarPasos.java
