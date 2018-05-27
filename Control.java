package BrazoRobótico;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;
import com.panamahitek.PanamaHitek_Arduino;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;

/*CLASE QUE HEREDA DE JPANEL Y CONTIENE LOS BOTONES QUE CONTROLAN
 * EL BRAAZO ROBÓTICO.*/
public class Control extends JPanel { // HEREDAR DE JPANEL

	private Font font = new Font("Franklin Gothic Demi Cond", Font.PLAIN, 20); 	// INSTANCIAR CLASE FONT
	private JLabel labelTitulo;													// DECLARAR JLABEL PARA EL TÍTULO
	private JPanel panelPinza, panelCodo, panelBrazo, panelBase;	// DECLARAR JPANEL PARA LOS DISTINTOS GRUPOS DE BOTONES
	// PINZAS
	private JButton btnPinzaAbrir, btnPinzaCerrar;			// DECLARAR BOTONES PARA LAS PINZAS
	// CODO
	private JButton btnCodoIzq, btnCodoDer;				// DECLARAR BOTONES PARA EL CODO
	// BRAZO
	private JButton btnBrazoIzq, btnBrazoDer;			// DECLARAR BOTONES PARA EL BRAZO
	// BASE
	private JButton btnBaseIzq, btnBaseDer;				// DECLARAR BOTONES PARA LA BASE
	private Timer timer;						// DECLARAR TIMER
	private String botonPresionado = "", mensaje = "1";		// VARIABLES STRINGS QUE ALMACENAN EL BOTÓN PRESIONADO Y UN TIPO DE MENSAJE
	public static PanamaHitek_Arduino pinzas = new PanamaHitek_Arduino();		// INSTANCIAR OBJETO PARA MANIPULAR PINZAS
	public static PanamaHitek_Arduino codo = new PanamaHitek_Arduino();		// INSTANCIAR OBJETO PARA MANIPULAR CODO
	public static PanamaHitek_Arduino brazo = new PanamaHitek_Arduino();		// INSTANCIAR OBJETO PARA MANIPULAR BRAZO
	public static PanamaHitek_Arduino base = new PanamaHitek_Arduino();		// INSTANCIAR OBJETO PARA MANIPULAR BASE
	private Manejadora evento = new Manejadora();					// INSTANCIAR CLASE MANEJADORA DE LOS EVENTOS

	public Control() {
		setLayout(new BorderLayout());			// ESTABLECER EL LAYOUT COMO BORDERLAYOUT
		
		add(getTitulo(), BorderLayout.NORTH);	// AGREGAR EN EL NORTE DEL PANEL EL PANEL DEL TÍTULO
		add(getBotones(), BorderLayout.CENTER);	// AGREGAR EN EL CENTRO DEL PANEL EL PANEL DE LOS BOTONES
		controlarBotones();			// LLAMAR AL MÉTODO QUE INICIALIZA LOS VALORES DEL TIMER
		
		realizarConexionArduinoPinzas();	// MÉTODO QUE REALIZA LA CONEXIÓN CON EL ARDUINO PARA LAS PINZAS
		realizarConexionArduinoCodo();		// MÉTODO QUE REALIZA LA CONEXIÓN CON EL ARDUINO PARA EL CODO
		realizarConexionArduinoBrazo();		// MÉTODO QUE REALIZA LA CONEXIÓN CON EL ARDUINO PARA EL BRAZO
		realizarConexionArduinoBase();		// MÉTODO QUE REALIZA LA CONEXIÓN CON EL ARDUINO PARA LA BASE
	}
	
	/*MÉTODO QUE RETORNA PANEL QUE CONTIENE TÍTULO*/
	private JPanel getTitulo() {
		JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));	// INSTANCIAR PANEL CON LAYOUT FLOWLAYOUT
		labelTitulo = new JLabel("Control");				// INSTANCIAR JLABEL PARA EL TÍTULO
		labelTitulo.setFont(font);					// ESTABLECER FUENTE DEL JLABEL
		panel.setBorder(new EmptyBorder(20, 0, 0, 0));			// ESTABLECER BORDE VACÍO PARA SEPARACIÓN ENTRE PÁNELES
		
		panel.add(labelTitulo);						// AGREGAR JLABEL AL PANEL
		return panel;							// RETORNAR PANEL
	}
	
	/*MÉTODO QUE RETORNA PANEL QUE CONTIENE LOS BOTONES*/
	private JPanel getBotones() {
		JPanel panel = new JPanel(new GridLayout(4, 1, 0, 20));		// INSTANCIAR PANEL CON LAYOUT GRIDLAYOUT
		panel.setBorder(new EmptyBorder(20, 50, 58, 30));		// ESTABLECER BORDE VACÍO AL PANEL PARA SEPARACIÓN ENTRE PÁNELES
		
		panel.add(getPinzas());		// AGREGAR PANEL DE PINZAS AL PANEL DE BOTONES
		panel.add(getCodo());		// AGREGAR PANEL DE CODO AL PANEL DE BOTONES
		panel.add(getBrazo());		// AGREGAR PANEL DE BRAZO AL PANEL DE BOTONES
		panel.add(getBase());		// AGREGAR PANEL DE BASE AL PANEL DE BOTONES
		return panel;			// RETORNAR PANEL
	}
	
	/*PANEL QUE RETORNA BOTONES QUE PERMITEN CONTROLAR LAS PINZAS DEL ROBOT*/
	private JPanel getPinzas() {
		JPanel panel = new JPanel(new GridLayout(2, 1, 5, 5));	// INSTANCIAR PANEL CON LAYOUT GRIDLAYOUT
		panelPinza = new JPanel(new BorderLayout());		// INSTANCIAR PANEL CON LAYOUT BORDERLAYOUT
		JLabel label = new JLabel("Pinzas");			// INSTANCIAR JLABEL PARA TÍTULO
		btnPinzaAbrir = new JButton("Abrir");			// INSTANCIAR JBUTTON QUE ABRIRÁ LAS PINZAS DEL ROBOT
		btnPinzaCerrar = new JButton("Cerrar");			// INSTANCIAR JBUTTON QUE CERRARÁ LAS PINZAS DEL ROBOT
		
		btnPinzaAbrir.addMouseListener(evento);			// AGREGAR EVENTO DE RATÓN AL BOTÓN
		btnPinzaCerrar.addMouseListener(evento);		// AGREGAR EVENTO DE RATÓN AL BOTÓN
		
		panel.add(btnPinzaAbrir);				// AGREGAR AL PANEL EL BOTÓN DE ABRIR
		panel.add(btnPinzaCerrar);				// AGREGAR AL PANEL EL BOTÓN DE CERRAR
		panelPinza.add(label, BorderLayout.NORTH);		// AGREGAR AL PANEL EL JLABEL EN EL NORTE
		panelPinza.add(panel, BorderLayout.CENTER);		// AGREGAR AL PANEL EL PANEL DE LOS BOTONES EN EL CENTRO
		return panelPinza;					// RETORNAR PANEL
	}
	
	/*MÉTODO QUE RETORNA BOTONES DEL CODO*/
	private JPanel getCodo() {
		JPanel panel = new JPanel(new GridLayout(1, 2, 5, 5));	// INSTANCIAR PANEL CON LAYOUT GRIDLAYOUT
		panelCodo = new JPanel(new BorderLayout());		// INSTANCIAR PANEL CON LAYOUT BORDERLAYOUT
		JLabel label = new JLabel("Codo");			// INSTANCIAR JLABEL PARA TÍTULO
		btnCodoIzq = new JButton("Arriba");			// INSTANCIAR JBUTTON QUE SUBIRÁ EL CODO DEL ROBOT
		btnCodoDer = new JButton("Abajo");			// INSTANCIAR JBUTTON QUE BAJARÁ EL CODO DEL ROBOT
		
		btnCodoIzq.addMouseListener(evento);			// AGREGAR EVENTO DE RATÓN AL BOTÓN
		btnCodoDer.addMouseListener(evento);			// AGREGAR EVENTO DE RATÓN AL BOTÓN
		
		panel.add(btnCodoIzq);					// AGREGAR BOTÓN AL PANEL
		panel.add(btnCodoDer);					// AGREGAR BOTÓN AL PANEL
		panelCodo.add(label, BorderLayout.NORTH)		// AGREGAR AL PANEL EL JLABEL EN EL NORTE
		panelCodo.add(panel, BorderLayout.CENTER);		// AGREGAR AL PANEL EL PANEL DE LOS BOTONES EN EL CENTRO
		return panelCodo;					// RETORNAR PANEL
	}
	
	/*MÉTODO QUE RETORNA PANEL CON BOTONES DEL BRAZO*/
	private JPanel getBrazo() {
		JPanel panel = new JPanel(new GridLayout(1, 2, 5, 5));	// INSTANCIAR PANEL CON LAYOUT GRIDLAYOUT
		panelBrazo = new JPanel(new BorderLayout());		// INSTANCIAR PANEL CON LAYOUT BORDERLAYOUT
		JLabel label = new JLabel("Brazo");			// INSTANCIAR JLABEL PARA TÍTULO
		btnBrazoIzq = new JButton("Abajo");			// INSTANCIAR JBUTTON QUE BAJARÁ EL BRAZO DEL ROBOT
		btnBrazoDer = new JButton("Arriba");			// INSTANCIAR JBUTTON QUE SUBIRÁ EL BRAZO DEL ROBOT
		
		btnBrazoIzq.addMouseListener(evento);			// AGREGAR EVENTO DE RATÓN AL BOTÓN
		btnBrazoDer.addMouseListener(evento);			// AGREGAR EVENTO DE RATÓN AL BOTÓN
		
		panel.add(btnBrazoIzq);					// AGREGAR BOTÓN AL PANEL
		panel.add(btnBrazoDer);					// AGREGAR BOTÓN AL PANEL
		panelBrazo.add(label, BorderLayout.NORTH);		// AGREGAR AL PANEL EL JLABEL EN EL NORTE
		panelBrazo.add(panel, BorderLayout.CENTER);		// AGREGAR AL PANEL EL PANEL DE LOS BOTONES EN EL CENTRO
		return panelBrazo;					// RETORNAR PANEL
	}
	
	/*MÉTODO QUE RETORNA PANEL CON BOTONES DE LA BASE*/
	private JPanel getBase() {
		JPanel panel = new JPanel(new GridLayout(1, 2, 5, 5));	// INSTANCIAR PANEL CON LAYOUT GRIDLAYOUT
		panelBase = new JPanel(new BorderLayout());		// INSTANCIAR PANEL CON LAYOUT BORDERLAYOUT
		JLabel label = new JLabel("Base");			// INSTANCIAR JLABEL PARA TÍTULO
		btnBaseIzq = new JButton("Izquierda");			// INSTANCIAR JBUTTON QUE GIRARÁ A LA IZQUIERDA LA BASE DEL ROBOT
		btnBaseDer = new JButton("Derecha");			// INSTANCIAR JBUTTON QUE GIRARÁ A LA DERECHA LA BASE DEL ROBOT
		
		btnBaseIzq.addMouseListener(evento);			// AGREGAR EVENTO DE RATÓN AL BOTÓN
		btnBaseDer.addMouseListener(evento);			// AGREGAR EVENTO DE RATÓN AL BOTÓN
		
		panel.add(btnBaseIzq);					// AGREGAR BOTÓN AL PANEL
		panel.add(btnBaseDer);					// AGREGAR BOTÓN AL PANEL
		panelBase.add(label, BorderLayout.NORTH);		// AGREGAR AL PANEL EL JLABEL EN EL NORTE
		panelBase.add(panel, BorderLayout.CENTER);		// AGREGAR AL PANEL EL PANEL DE LOS BOTONES EN EL CENTRO
		return panelBase;					// RETORNAR PANEL
	}
	
	/*MÉTODO QUE CONTROLA EL TIMER PARA ENVIAR VALORES AL ARDUINO MIENTRAS SE PRESIONE UN BOTÓN*/
	private void controlarBotones() {
		timer = new Timer(100, new ActionListener() { // INICIALIZACIÓN DEL TIMER

			@Override
			public void actionPerformed(ActionEvent ev) {
				String[] valores = botonPresionado.split("_"); // OBTENER VALORES SEGÚN EL BOTÓN QUE SE HAYA PRESIONADO
				
				try {
					switch(valores[0]) { // EVALUAR LA POSICIÓN 0 DEL ARREGLO 'VALORES'
						case "Pinza": pinzas.sendData(valores[1].equals("Abrir") ? "1" : "0"); 	// SI EL VALOR ES 'ABRIR' MANDAR UN 1, SI NO, UN 0
							break;
						case "Codo": codo.sendData(valores[1].equals("Arriba") ? "1" : "0");	// SI EL VALOR ES 'ARRIBA' MANDAR UN 1, SI NO, UN 0
							break;
						case "Brazo": brazo.sendData(valores[1].equals("Arriba") ? "1" : "0");	// SI EL VALOR ES 'ARRIBA' MANDAR UN 1, SI NO, UN 0
							break;
						case "Base": base.sendData(valores[1].equals("Izquierda") ? "1" : "0");	// SI EL VALOR ES 'IZQUIERDA' MANDAR UN 1, SI NO, UN 0
							break;
					}
				} catch(Exception ex) {
					System.out.println(ex.getMessage()); // IMPRIMIR EXCEPCIÓN
				}
			}
			
		});
	}
	
	/*MÉTODO QUE REALIZA LA CONEXIÓN CON ARDUINO PARA LAS PINZAS*/
	@SuppressWarnings("deprecation")
	private void realizarConexionArduinoPinzas() {
		try {
			pinzas.ArduinoRXTX("COM3", 2000, 9600, evento);	// REALIZAR CONEXIÓN CON ARDUINO Y REGISTRAR EVENTO 'TEMP'
		} catch (Exception e) {
			System.out.println(e.getMessage()); 		// MOSTRAR MENSAJE DE ERROR
		}
	}
	
	/*MÉTODO QUE REALIZA LA CONEXIÓN CON ARDUINO PARA EL CODO*/
	@SuppressWarnings("deprecation")
	private void realizarConexionArduinoCodo() {
		try {
			codo.ArduinoRXTX("COM4", 2000, 9600, evento);	// REALIZAR CONEXIÓN CON ARDUINO Y REGISTRAR EVENTO 'TEMP'
		} catch (Exception e) {
			System.out.println(e.getMessage()); 		// MOSTRAR MENSAJE DE ERROR
		}
	}
	
	/*MÉTODO QUE REALIZA LA CONEXIÓN CON ARDUINO PARA EL BRAZO*/
	@SuppressWarnings("deprecation")
	private void realizarConexionArduinoBrazo() {
		try {
			brazo.ArduinoRXTX("COM10", 2000, 9600, evento);	// REALIZAR CONEXIÓN CON ARDUINO Y REGISTRAR EVENTO 'TEMP'
		} catch (Exception e) {
			System.out.println(e.getMessage()); 		// MOSTRAR MENSAJE DE ERROR
		}
	}
	
	/*MÉTODO QUE REALIZA LA CONEXIÓN CON ARDUINO PARA LA BASE*/
	@SuppressWarnings("deprecation")
	private void realizarConexionArduinoBase() {
		try {
			base.ArduinoRXTX("COM5", 2000, 9600, evento);	// REALIZAR CONEXIÓN CON ARDUINO Y REGISTRAR EVENTO 'TEMP'
		} catch (Exception e) {
			System.out.println(e.getMessage()); 		// MOSTRAR MENSAJE DE ERROR
		}
	}
	
	/*CLASE INTERNA QUE MANEJA LOS EVENTOS DE RATÓN Y DE RECIBIR DATOS DE ARDUINO*/
	private class Manejadora extends MouseAdapter implements SerialPortEventListener {

		@Override
		public void mouseClicked(MouseEvent ev) {
			Registro.registroMovimientos.append(botonPresionado); // CADA QUE SE DE CLIC SOBRE UN BOTÓN, CONCATENAR EN EL TEXTAREA LA INSTRUCCIÓN
		}

		@Override
		public void mousePressed(MouseEvent ev) {
			if(ev.getSource() == btnPinzaAbrir)		// SI SE PRESIONÓ 'ABRIR PINZAS'
				botonPresionado = "Pinza_Abrir";	// LA VARIABLE 'BOTÓNPRESIONADO' CAMBIA
			
			if(ev.getSource() == btnPinzaCerrar)	// SI SE PRESIONÓ 'CERRAR PINZAS'
				botonPresionado = "Pinza_Cerrar";	// LA VARIABLE 'BOTÓNPRESIONADO' CAMBIA
			
			if(ev.getSource() == btnCodoIzq)		// SI SE PRESIONÓ 'ARRIBA CODO'
				botonPresionado = "Codo_Arriba";	// LA VARIABLE 'BOTÓNPRESIONADO' CAMBIA
			
			if(ev.getSource() == btnCodoDer)		// SI SE PRESIONÓ 'ABAJO CODO'
				botonPresionado = "Codo_Abajo";		// LA VARIABLE 'BOTÓNPRESIONADO' CAMBIA
			
			if(ev.getSource() == btnBrazoIzq)		// SI SE PRESIONÓ 'ABAJO BRAZO'
				botonPresionado = "Brazo_Abajo";	// LA VARIABLE 'BOTÓNPRESIONADO' CAMBIA
			
			if(ev.getSource() == btnBrazoDer)		// SI SE PRESIONÓ 'ARRIBA BRAZO'
				botonPresionado = "Brazo_Arriba";	// LA VARIABLE 'BOTÓNPRESIONADO' CAMBIA
			
			if(ev.getSource() == btnBaseIzq)		// SI SE PRESIONÓ 'IZQUIERDA BASE'
				botonPresionado = "Base_Izquierda";	// LA VARIABLE 'BOTÓNPRESIONADO' CAMBIA
			
			if(ev.getSource() == btnBaseDer)		// SI SE PRESIONÓ 'DERECHA BASE'
				botonPresionado = "Base_Derecha";	// LA VARIABLE 'BOTÓNPRESIONADO' CAMBIA
			
			timer.start();							// EMPEZAR EL TIMER
		}

		@Override
		public void mouseReleased(MouseEvent ev) {
			timer.stop();					// PARAR TIMER
			Registro.registroMovimientos.append(botonPresionado + "\n"); // CONCATENAR EN TEXTAREA EL VALOR DE 'BOTONPRESIONADO'
			botonPresionado = "";				// LIMPIAR VARIABLE 'BOTONPRESIONADO'
		}

		@Override
		public void serialEvent(SerialPortEvent ev) {			
			if(pinzas.isMessageAvailable())			// SI HAY UN MENSAJE DESDE ARDUINO PARA LAS PINZAS
				mensaje = pinzas.printMessage();	// GUARDAR EN 'MENSAJE' EL MENSAJE
			
			if(codo.isMessageAvailable())			// SI HAY UN MENSAJE DESDE ARDUINO PARA EL CODO
				mensaje = codo.printMessage();		// GUARDAR EN 'MENSAJE' EL MENSAJE
			
			if(brazo.isMessageAvailable())			// SI HAY UN MENSAJE DESDE ARDUINO PARA EL BRAZO
				mensaje = brazo.printMessage();		// GUARDAR EN 'MENSAJE' EL MENSAJE
			
			if(base.isMessageAvailable())			// SI HAY UN MENSAJE DESDE ARDUINO PARA LA BASE
				mensaje = base.printMessage();		// GUARDAR EN 'MENSAJE' EL MENSAJE
			
			if(mensaje.equals("0"))				// SI EL MENSAJE ES IGUAL A 0
				EjecutarPasos.interrumpir = false;	// CAMBIAR BANDERA DE LA CLASE 'EJECUTARPASOS" A FALSE
			else						// SI ES CUALQUIER OTRO VALOR
				EjecutarPasos.interrumpir = true;	// CAMBIAR BANDERA DE LA CLASE 'EJECUTARPASOS' A TRUE
		}
		
	}

}
