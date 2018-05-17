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

public class Control extends JPanel {

	/**
	 * 
	 */
	// implementa el interfaz Serializable, para leer cualquier otro dispositivo de entrada/salida
	private static final long serialVersionUID = 1L;
	private Font font = new Font("Franklin Gothic Demi Cond", Font.PLAIN, 20);
	private JLabel labelTitulo;
	private JPanel panelPinza, panelCodo, panelBrazo, panelBase;
	// PINZAS
	private JButton btnPinzaAbrir, btnPinzaCerrar;
	// CODO
	private JButton btnCodoIzq, btnCodoDer;
	// BRAZO
	private JButton btnBrazoIzq, btnBrazoDer;
	// BASE
	private JButton btnBaseIzq, btnBaseDer;
	private Timer timer;
	private String botonPresionado = "";
	
	//se instancia la librerioa PanamaHitek para Arduino
	private PanamaHitek_Arduino pinzas = new PanamaHitek_Arduino();
	private PanamaHitek_Arduino codo = new PanamaHitek_Arduino();
	private PanamaHitek_Arduino brazo = new PanamaHitek_Arduino();
	private PanamaHitek_Arduino base = new PanamaHitek_Arduino();
	private Manejadora evento = new Manejadora();
	
	
	public Control() {
		setLayout(new BorderLayout());
		
		add(getTitulo(), BorderLayout.NORTH);
		add(getBotones(), BorderLayout.CENTER);
		controlarBotones();
		
		realizarConexionArduinoPinzas();
		realizarConexionArduinoCodo();
		realizarConexionArduinoBrazo();
		realizarConexionArduinoBase();
	}
	
	private JPanel getTitulo() {
		JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		labelTitulo = new JLabel("Control");
		labelTitulo.setFont(font);
		panel.setBorder(new EmptyBorder(20, 0, 0, 0));
		
		panel.add(labelTitulo);
		return panel;
	}
	
	private JPanel getBotones() {
		JPanel panel = new JPanel(new GridLayout(4, 1, 0, 20));
		panel.setBorder(new EmptyBorder(20, 50, 58, 30));
		
		panel.add(getPinzas());
		panel.add(getCodo());
		panel.add(getBrazo());
		panel.add(getBase());
		
		return panel;
	}
	
	
	// Panel de la interfaz de las pinzas del robot
	private JPanel getPinzas() {
		JPanel panel = new JPanel(new GridLayout(2, 1, 5, 5));
		panelPinza = new JPanel(new BorderLayout());
		JLabel label = new JLabel("Pinzas");
		btnPinzaAbrir = new JButton("Abrir");
		btnPinzaCerrar = new JButton("Cerrar");
		
		btnPinzaAbrir.addMouseListener(evento);
		btnPinzaCerrar.addMouseListener(evento);
		
		panel.add(btnPinzaAbrir);
		panel.add(btnPinzaCerrar);
		panelPinza.add(label, BorderLayout.NORTH);
		panelPinza.add(panel, BorderLayout.CENTER);
		return panelPinza;
	}
	
	//Panel de la interfaz del codo  del robot
	private JPanel getCodo() {
		JPanel panel = new JPanel(new GridLayout(1, 2, 5, 5));
		panelCodo = new JPanel(new BorderLayout());
		JLabel label = new JLabel("Codo");
		btnCodoIzq = new JButton("Abajo");
		btnCodoDer = new JButton("Arriba");
		
		btnCodoIzq.addMouseListener(evento);
		btnCodoDer.addMouseListener(evento);
		
		panel.add(btnCodoIzq);
		panel.add(btnCodoDer);
		panelCodo.add(label, BorderLayout.NORTH);
		panelCodo.add(panel, BorderLayout.CENTER);
		return panelCodo;
	}
	
	//Panel de la interfaz del brazo del robot 
	private JPanel getBrazo() {
		JPanel panel = new JPanel(new GridLayout(1, 2, 5, 5));
		panelBrazo = new JPanel(new BorderLayout());
		JLabel label = new JLabel("Brazo");
		btnBrazoIzq = new JButton("Abajo");
		btnBrazoDer = new JButton("Arriba");
		
		btnBrazoIzq.addMouseListener(evento);
		btnBrazoDer.addMouseListener(evento);
		
		panel.add(btnBrazoIzq);
		panel.add(btnBrazoDer);
		panelBrazo.add(label, BorderLayout.NORTH);
		panelBrazo.add(panel, BorderLayout.CENTER);
		return panelBrazo;
	}
	
	//Panel de la interfaz de la base del robot
	private JPanel getBase() {
		JPanel panel = new JPanel(new GridLayout(1, 2, 5, 5));
		panelBase = new JPanel(new BorderLayout());
		JLabel label = new JLabel("Base");
		btnBaseIzq = new JButton("Izquierda");
		btnBaseDer = new JButton("Derecha");
		
		btnBaseIzq.addMouseListener(evento);
		btnBaseDer.addMouseListener(evento);
		
		panel.add(btnBaseIzq);
		panel.add(btnBaseDer);
		panelBase.add(label, BorderLayout.NORTH);
		panelBase.add(panel, BorderLayout.CENTER);
		return panelBase;
	}
	
	//el siguiente metodo manipulara el robot dependiendo del boton de la interfaz que se haya oprimido
	private void controlarBotones() {
		timer = new Timer(100, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent ev) {
				String[] valores = botonPresionado.split("_");
				
				try {
					switch(valores[0]) {
						case "Pinza":
							switch(valores[1]) {
								case "Abrir": pinzas.sendData("1");
									break;
								case "Cerrar": pinzas.sendData("0");
									break;
							}
							break;
						case "Codo":
							switch(valores[1]) {
								case "Arriba": codo.sendData("1");
									break;
								case "Abajo": codo.sendData("0");
									break;
							}
							break;
						case "Brazo":
							switch(valores[1]) {
								case "Arriba": brazo.sendData("1");
									break;
								case "Abajo": brazo.sendData("0");
									break;
							}
							break;
						case "Base":
							switch(valores[1]) {
								case "Izquierda": base.sendData("1");
									break;
								case "Derecha": base.sendData("0");
									break;
							}
							break;
					}
				} catch(Exception ex) {
					System.out.println(ex.getMessage());
				}
			}
			
		});
	}
	
	/*MÉTODO QUE REALIZA LA CONEXIÓN CON ARDUINO*/
	@SuppressWarnings("deprecation")
	private void realizarConexionArduinoPinzas() {
		try {
			pinzas.ArduinoRXTX("COM4", 2000, 9600, evento); 	// REALIZAR CONEXIÓN CON ARDUINO Y REGISTRAR EVENTO 'TEMP'
		} catch (Exception e) {
			System.out.println(e.getMessage()); 				// MOSTRAR MENSAJE DE ERROR
		}
	}
	
	@SuppressWarnings("deprecation")
	private void realizarConexionArduinoCodo() {
		try {
			codo.ArduinoRXTX("COM9", 2000, 9600, evento); 	// REALIZAR CONEXIÓN CON ARDUINO Y REGISTRAR EVENTO 'TEMP'
		} catch (Exception e) {
			System.out.println(e.getMessage()); 				// MOSTRAR MENSAJE DE ERROR
		}
	}
	
	@SuppressWarnings("deprecation")
	private void realizarConexionArduinoBrazo() {
		try {
			brazo.ArduinoRXTX("COM3", 2000, 9600, evento); 	// REALIZAR CONEXIÓN CON ARDUINO Y REGISTRAR EVENTO 'TEMP'
		} catch (Exception e) {
			System.out.println(e.getMessage()); 				// MOSTRAR MENSAJE DE ERROR
		}
	}
	
	@SuppressWarnings("deprecation")
	private void realizarConexionArduinoBase() {
		try {
			base.ArduinoRXTX("COM3", 2000, 9600, evento); 	// REALIZAR CONEXIÓN CON ARDUINO Y REGISTRAR EVENTO 'TEMP'
		} catch (Exception e) {
			System.out.println(e.getMessage()); 				// MOSTRAR MENSAJE DE ERROR
		}
	}
	
	
	//Eventos para manipular el robot
	private class Manejadora extends MouseAdapter implements SerialPortEventListener {

		@Override
		public void mouseClicked(MouseEvent ev) {
			Registro.registroMovimientos.append(botonPresionado);
		}

		@Override
		public void mousePressed(MouseEvent ev) {
			if(ev.getSource() == btnPinzaAbrir)
				botonPresionado = "Pinza_Abrir";
			
			if(ev.getSource() == btnPinzaCerrar)
				botonPresionado = "Pinza_Cerrar";
			
			if(ev.getSource() == btnCodoIzq)
				botonPresionado = "Codo_Arriba";
			
			if(ev.getSource() == btnCodoDer)
				botonPresionado = "Codo_Abajo";
			
			if(ev.getSource() == btnBrazoIzq)
				botonPresionado = "Brazo_Abajo";
			
			if(ev.getSource() == btnBrazoDer)
				botonPresionado = "Brazo_Arriba";
			
			if(ev.getSource() == btnBaseIzq)
				botonPresionado = "Base_Izquierda";
			
			if(ev.getSource() == btnBaseDer)
				botonPresionado = "Base_Derecha";
			
			timer.start();
		}

		@Override
		public void mouseReleased(MouseEvent ev) {
			timer.stop();
			Registro.registroMovimientos.append(botonPresionado + "\n");
			botonPresionado = "";
		}

		@Override
		public void serialEvent(SerialPortEvent ev) {
			
		}
		
	}

}
