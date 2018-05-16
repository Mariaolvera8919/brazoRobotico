package BrazoRobótico;

import java.awt.GridLayout;
import javax.swing.JPanel;

public class Contenedora extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Contenedora() {
		setLayout(new GridLayout(1, 2, 5, 5));
		
		add(new Control());
		add(new Registro());
	}

}
