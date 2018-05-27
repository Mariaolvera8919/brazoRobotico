package BrazoRobótico;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

/*CLASE QUE HEREDA DE JFRAME PARA MOSTRAR TABLA CON
 * TODOS LOS PASOS REGISTRADOS Y QUE POSTERIORMENTE 
 * EJECUTARÁ AUTOMÁTICAMENTE DE UNO POR UNO, MOSTRANDO
 * EN UNA BARRA DE PROGRESO EL PROCESO.*/
public class EjecutarPasos extends JFrame {

	private String[] columnas = {"#", "Acción"};	// INSTANCIAR EL ARREGLO QUE CONTIENE LOS NOMBRES DE LAS COLUMNAS
	private String[] filas;				// DECLARAR EL ARREGLO DE LAS FILAS
	private JLabel labelTitulo;			// DECLARAR JLABEL
	private DefaultTableModel modelo;		// DECLARAR MODELO DE LA TABLA
	private JTable tabla;				// DECLARAR JTABLE
	private JProgressBar barra;			// DECLARAR JPROGRESSBAR
	private Timer timer;				// DECLARAR TIMER
	private Thread t1;				// DECLARAR HILO
	private Hilo hilo = new Hilo();			// INSTANCIAR CLASE QUE IMPLEMENTA 'RUNNABLE'
	private int contador = 1;			// INSTANCIAR CONTADOR QUE CUENTA LOS PROCESOS EJECUTADOS
	public static boolean interrumpir = false;	// INSTANCIAR BANDERA COMO PÚBLICA Y ESTÁTICA QUE CAMBIA CUANDO HAY UNA INTERRUPCIÓN

	public EjecutarPasos(String[] filas) {
		super("Ejecutar pasos");		// ESTABLECER TÍTULO DEL FRAME
		setLayout(new BorderLayout());		// ESTABLECER LAYOUT DEL FRAME
		
		this.filas = filas;			// GUARDAR ARREGLO DEL PARÁMETRO EN EL DE LA CLASE
		ejecutarPasos();			// LLAMA MÉTODO QUE CONTIENE EL TIMER
		add(getNorte(), BorderLayout.NORTH);	// AGREGA PANEL AL NORTE
		add(getCentro(), BorderLayout.CENTER);	// AGREGA PANEL AL CENTRO
		add(getSur(), BorderLayout.SOUTH);	// AGREGA PANEL AL SUR
		
		t1 = new Thread(hilo);	// INSTANCIAR OBJETO THREAD Y PASARLE COMO PARÁMETRO EL OBJETO 'Hilo'
		t1.start();		// INICIAR EL HILO
	}
	
	/*MÉTODO QUE RETORNA PANEL QUE CONTIENE EL TÍTULO*/
	private JPanel getNorte() {
		JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));	// INSTANCIAR EL PANEL CON LAYOUT FLOWLAYOUT
		labelTitulo = new JLabel("Ejecutar Pasos");			// INSTANCIAR EL JLABEL
		labelTitulo.setFont(new Font("Franklin Gothic Demi Cond", Font.PLAIN, 20)); // ESTABLECER LA FUENTE PARA EL JLABEL
		
		panel.add(labelTitulo);	// AGREGAR JLABEL AL PANEL
		return panel;		// RETORNAR PANEL
	}
	
	/*MÉTODO QUE RETORNA PANEL QUE CONTIENE LA TABLA*/
	private JPanel getCentro() {
		JPanel panel = new JPanel(new BorderLayout());	// INSTANCIAR PANEL CON LAYOUT BORDERLAYOUT
		modelo = new DefaultTableModel();		// INSTANCIAR EL DEFAULTTABLEMODEL
		tabla = new JTable(modelo);			// INSTANCIAR TABLA CON SU MODELO COMO PARÁMETRO
		
		panel.setBorder(new EmptyBorder(10, 10, 0, 10));	// ESTABLECER BORDE AL PANEL
		modelo.setColumnIdentifiers(columnas);			// ESTABLECER LAS COLUMNAS DE LA TABLE
		for(int i = 0; i < filas.length; i++)			// RECORRER LAS FILAS (ARREGLO) QUE RECIBE LA CLASE
			modelo.addRow(new Object[] {new Integer(i + 1), filas[i]});	// AGREGAR FILA CON LA INSTRUCCIÓN DE ESA POSICIÓN DEL ARREGLO
		
		panel.add(new JScrollPane(tabla));	// AGREGAR TABLE
		return panel;				// RETORNAR PANEL
	}
	
	/*MÉTODO QUE RETORNA PANEL QUE CONTIENE LA BARRA DE PROGRESO*/
	private JPanel getSur() {
		JPanel panel = new JPanel(new BorderLayout());		// INSTANCIAR JPANEL CON LAYOUT BORDERLAYOUT
		barra = new JProgressBar();				// INSTANCIA JPROGRESSBAR
		
		panel.setBorder(new EmptyBorder(10, 10, 10, 10));	// ESTABLECER BORDE VACÍO AL PANEL
		barra.setStringPainted(true);				// ESTABLECER COMO VERDADERO EL VALOR DEL PROGRESO
		barra.setMaximum(100);					// ESTABLECER EL VALOR MÁXIMO EN 100
		barra.setMinimum(0);					// ESTABLECER EL VALOR MÍNIMO EN 0
		barra.setForeground(Color.green.darker());		// PINTAR LA BARRA DE VERDE
		
		panel.add(barra, BorderLayout.CENTER);			// AGREGAR LA BARRA EN EL CENTRO DEL PANEL
		return panel;						// RETORNAR PANEL
	}
	
	/*CLASE INTERNA QUE HEREDA DE RUNNABLE Y CUYO MÉTODO INICIA EL TIMER PARA EJECUTAR INSTRUCCIÓN POR INSTRUCCIÓN*/
	private class Hilo implements Runnable {

		@Override
		public void run() {
			timer.start();	// INICIAR EL TIMER
		}
		
	}
	
	/*MÉTODO QUE EJECUTA CADA INSTRUCCIÓN EN LA TABLA Y QUE MUESTRA EL PROGRESO EN LA JPROGRESSBAR*/
	private void ejecutarPasos() {
		timer = new Timer(500, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent ev) {				
				if(!interrumpir && modelo.getRowCount() > 0) {		// SI NO HAY INTERRUPCIÓN Y AÚN HAY FILAS EN LA TABLA
					barra.setString("Ejecutando #" + contador++);	// CAMBIAR EL TÍTULO DE LA BARRA
					barra.setForeground(Color.GREEN.darker());	// CAMBIAR EL COLOR DE LA BARRA
					String[] valores = modelo.getValueAt(0, 1).toString().split("_"); // OBTENER ARREGLO CON LA INSTRUCCIÓN EN LA PRIMERA FILA DE LA TABLA
					
					try {
						barra.setValue(30);	// CAMBIAR PROGRESO A UN 30%
						Thread.sleep(500);	// DORMIR EL HILO ACTUAL POR MEDIO SEGUNDO
						
						/*EVALUAR EL VALOR DE LA PRIMERA POSICIÓN DEL ARREGLO*/
						switch(valores[0]) {
							case "Pinza": Control.pinzas.sendData(valores[1].equals("Abrir") ? "3" : "2");	// ENVIAR VALOR AL ARDUINO EN FUNCIÓN DEL MENSAJE
								break;
							case "Codo": Control.codo.sendData(valores[1].equals("Arriba") ? "3" : "2");	// ENVIAR VALOR AL ARDUINO EN FUNCIÓN DEL MENSAJE
								break;
							case "Brazo": Control.brazo.sendData(valores[1].equals("Arriba") ? "3" : "2");	// ENVIAR VALOR AL ARDUINO EN FUNCIÓN DEL MENSAJE
								break;
							case "Base": Control.base.sendData(valores[1].equals("Izquierda") ? "3" : "2");	// ENVIAR VALOR AL ARDUINO EN FUNCIÓN DEL MENSAJE
								break;
						}
						
						modelo.removeRow(0);	// UNA VEZ QUE SE HIZO EL PROCESO, QUITAR LA FILA QUE YA SE HA EJECUTADO
						barra.setValue(100);	// PONER VALOR EN 100 DE LA BARRA DE PROGRESO
					} catch(Exception ex) {
						System.out.println(ex.getMessage());	// IMPRIMIR MENSAJE DE EXCEPCIÓN
					}
				} else if(interrumpir) {	// SI SE HA HECHO UNA INTERRUPCIÓN
					barra.setString("Se ha interrumpido el proceso");	// CAMBIAR EL MENSAJE EN LA BARRA DE PROGRESO
					barra.setValue(50);					// ESTABLECER VALOR EN 50%
					barra.setForeground(Color.red.darker());		// CAMBIAR COLOR A ROJO
				} else {	// SI SE HAN TERMINADO DE EJECUTAR TODAS LAS INSTRUCCIONES DE LA TABLA
					timer.stop();	// PARAR EL TIMER
					dispose();	// CERRAR VENTANA
					JOptionPane.showMessageDialog(null, "Se ha terminado.");	// ENVIAR MENSAJE DE QUE SE HA EJECUTADO TODO EXITOSAMENTE
				}
			}
			
		});
	}

}
