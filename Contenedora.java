package BrazoRobótico;

import java.awt.GridLayout;
import javax.swing.JPanel;

/*CLASE QUE HEREDA DE JPANEL PARA ALMACENAR DOS PANELES EXTRAS QUE SON:
 * -PANEL PARA BOTONES DE CONTROL DEL ROBOT
 * -PANEL PARA EL REGISTRO DE LOS MOVIMIENTOS DEL ROBOT*/
public class Contenedora extends JPanel {

	public Contenedora() {
		setLayout(new GridLayout(1, 2, 5, 5)); 	//ESTABLECER EL LAYOUT GRIDLAYOUT
		
		add(new Control());			// AGREGAR AL PANEL EL PANEL "CONTROL"
		add(new Registro());			// AGREGAR AL PANEL EL PANEL "REGISTRO"
	}

}
