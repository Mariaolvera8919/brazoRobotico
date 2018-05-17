package BrazoRobótico;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.UIManager;

public class TestBrazo {

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (Exception e) {}
		
		JFrame frame = new JFrame("Control Brazo Robótico");
		
		frame.setLayout(new BorderLayout()); 					// ESTABLECER LAYOUT 
		frame.setSize(900, 600); 						// ESTABLECER TAMAÑO DE LA VENTANA
		frame.setLocationRelativeTo(null); 					// ESTABLECER VENTANA AL CENTRO DE LA PANTALLA
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 			// TERMINAR PROGRAMA AL CERRAR VENTANA
		frame.add(new Contenedora()); 						// AGREGAR PANEL AL JFRAME
		frame.setVisible(true); 						// HACER VENTANA VISIBLE
	}

}
