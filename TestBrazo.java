/*PROGRAMA EN JAVA QUE PERMITE CONTROLAR UN BRAZO ROBÓTICO
 * ENVIANDO DATOS AL ARDUINO PARA MANIPULAR AL MISMO.*/
package BrazoRobótico;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.UIManager;

public class TestBrazo {

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel"); // ESTABLECER EL LOOK AND FEEL NIMBUS
		} catch (Exception e) {}
		
		JFrame frame = new JFrame("Control Brazo Robótico");			// CREAR OBJETO JFRAME
		
		frame.setLayout(new BorderLayout()); 					// ESTABLECER LAYOUT 
		frame.setSize(1200, 600); 						// ESTABLECER TAMAÑO DE LA VENTANA
		frame.setLocationRelativeTo(null); 					// ESTABLECER VENTANA AL CENTRO DE LA PANTALLA
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 			// TERMINAR PROGRAMA AL CERRAR VENTANA
		frame.add(new Contenedora()); 						// AGREGAR PANEL AL JFRAME
		frame.setVisible(true); 						// HACER VENTANA VISIBLE
	}

}
