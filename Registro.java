package BrazoRobótico;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

public class Registro extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Font font = new Font("Franklin Gothic Demi Cond", Font.PLAIN, 20);
	private JPanel panelNorte, panelCentro;
	private JLabel lblTitulo;
	public static JTextArea registroMovimientos;

	//panel norte y centro de la clase regristro
	public Registro() {
		setLayout(new BorderLayout());
		
		add(getNorte(), BorderLayout.NORTH);
		add(getCentro(), BorderLayout.CENTER);
	}
	
	//panel y dimensiones del label del titulo
	private JPanel getNorte() {
		JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		panelNorte = new JPanel(new BorderLayout());
		lblTitulo = new JLabel("Registro de Movimientos");
		lblTitulo.setFont(font);
		panelNorte.setBorder(new EmptyBorder(20, 0, 25, 0));
		
		panel.add(lblTitulo);
		panelNorte.add(panel, BorderLayout.CENTER);
		return panelNorte;
	}
	
	
	//panel para el registro d emovimientos del brazo robotico
	private JPanel getCentro() {
		JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		panelCentro = new JPanel(new BorderLayout());
		registroMovimientos = new JTextArea(23, 45);
		registroMovimientos.setLineWrap(true);
		panelCentro.setBorder(new EmptyBorder(0, 0, 0, 40));
		
		panel.add(new JScrollPane(registroMovimientos));
		panelCentro.add(panel, BorderLayout.CENTER);
		return panelCentro;
	}

}
