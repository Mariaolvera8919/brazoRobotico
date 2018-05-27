package BrazoRobótico;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

/*CLASE QUE HEREDA DE JPANEL Y CONTIENE EL JTEXTAREA
 * DONDE SE REGISTRAN TODOS LOS MOVIMIENTOS QUE SE
 * HACEN PARA EL BRAZO ROBÓTICO.*/
public class Registro extends JPanel {

	private Font font = new Font("Franklin Gothic Demi Cond", Font.PLAIN, 20);	// INSTANCIAR CLASE FONT
	private JPanel panelNorte, panelCentro;						// DECLARAR OBJETOS JPANEL
	private JLabel lblTitulo;					// DECLARAR OBJETO JLABEL
	private JButton btnEjecutarPasos;				// DECLARAR OBJETO JBUTTON
	public static JTextArea registroMovimientos;			// DECLARAR OBJETO JTEXTAREA COMO PÚBLICO Y ESTÁTICO

	public Registro() {
		setLayout(new BorderLayout());		// ESTABLECER LAYOUT DEL PANEL COMO BORDERLAYOUT
		
		add(getNorte(), BorderLayout.NORTH);	// AGREGAR EL PANEL AL NORTE
		add(getCentro(), BorderLayout.CENTER);	// AGREGAR EL PANEL AL CENTRO
		add(getSur(), BorderLayout.SOUTH);	// AGREGAR EL PANEL AL SUR
	}
	
	/*MÉTODO QUE RETORNA PANEL QUE CONTIENE EL TÍTULO*/
	private JPanel getNorte() {
		JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));	// INSTANCIAR JPANEL CON LAYOUT FLOWLAYOUT
		panelNorte = new JPanel(new BorderLayout());			// INSTANCIAR JPANEL CON LAYOUT BORDERLAYOUT
		lblTitulo = new JLabel("Registro de Movimientos");		// INSTANCIAR JLABEL
		lblTitulo.setFont(font);					// ESTABLECER FUENTE AL JLABEL
		panelNorte.setBorder(new EmptyBorder(20, 0, 25, 0));		// ESTABLECER BORDE VACÍO AL PANEL
		
		panel.add(lblTitulo);						// AGREGAR AL PANEL EL JLABEL
		panelNorte.add(panel, BorderLayout.CENTER);			// AGREGAR EL PANEL DEL TÍTULO EN EL CENTRO
		return panelNorte;						// RETORNAR PANEL
	}
	
	/*MÉTODO QUE RETORNA PANEL QUE CONTIENE EL JTEXTAREA*/
	private JPanel getCentro() {
		JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));	// INSTANCIAR JPANEL CON LAYOUT FLOWLAYOUT
		panelCentro = new JPanel(new BorderLayout());			//INSTANCIAR JPANEL CON LAYOUT BORDERLAYOUT
		registroMovimientos = new JTextArea(23, 45);			// INSTANCIAR JTEXTAREA
		registroMovimientos.setLineWrap(true);				// ESTABLECER SALTO DE LÍNEA AUTOMÁTICO AL JTEXTAREA
		panelCentro.setBorder(new EmptyBorder(0, 0, 0, 40));		// ESTABLECER BORDE VACÍO AL JPANEL
		
		panel.add(new JScrollPane(registroMovimientos));		// AGREGAR EL TEXT AREA DENTRO DE UN JSCROLLPANE
		panelCentro.add(panel, BorderLayout.CENTER);			// AGREGAR EL PANEL EN EL CENTRO
		return panelCentro;						// RETORNAR JPANEL
	}
	
	/*MÉTODO QUE RETORNA PANEL CON UN BOTÓN*/
	private JPanel getSur() {
		JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));	// INSTANCIAR JPANEL CON LAYOUT FLOWLAYOUT
		btnEjecutarPasos = new JButton("Ejecutar pasos");		//INSTANCIAR BOTÓN
		btnEjecutarPasos.addActionListener(new ActionListener() {	// REGISTRAR EVENTO DEL BOTÓN CON CLASE ANÓNIMA

			@Override
			public void actionPerformed(ActionEvent ev) {
				// AL OCURRIR EL EVENTO, QUE INSTANCIE LA CLASE 'EJECUTARPASOS' Y QUE LE PASE COMO PARÁMETRO
				// UN ARREGLO CON TODO LO REGISTRADO EN EL TEXTAREA
				EjecutarPasos ejecutar = new EjecutarPasos(registroMovimientos.getText().split("\n"));
				
				ejecutar.setSize(700, 500);				// ESTABLECER TAMAÑO DEL FRAME
				ejecutar.setLocationRelativeTo(null);			// ESTABLECER UBICACIÓN EN EL CENTRO
				ejecutar.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);	// QUE CIERRE TODO RECURSO AL CERRAR VENTANA
				ejecutar.setVisible(true);		// HACER VENTANA VISIBLE
			}
			
		});
		
		panel.add(btnEjecutarPasos);	// AGREGAR BOTÓN AL PANEL
		return panel;			// RETORNAR PANEL
	}

}
