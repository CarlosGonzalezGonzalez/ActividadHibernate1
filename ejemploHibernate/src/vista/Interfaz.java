package vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.GridLayout;

public class Interfaz {

	private JFrame frame;
	private JTextField textFieldId;
	private JTextField textFieldPregunta;
	private JTextField respuesta1;
	private JTextField respuesta2;
	private JTextField respuesta3;
	private JTextField respuesta4;
	
	private JButton botonAgregar;
	private JButton botonBuscar;
	private JButton botonActualizar;
	private JButton botonEliminar;

	/**
	 * Create the application.
	 */
	public Interfaz() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.NORTH);
		
		JLabel labelId = new JLabel("Id:");
		panel.add(labelId);
		
		textFieldId = new JTextField();
		textFieldId.setColumns(10);
		panel.add(textFieldId);
		
		JLabel labelPregunta = new JLabel("Introduzca la pregunta:");
		panel.add(labelPregunta);
		
		textFieldPregunta = new JTextField();
		textFieldPregunta.setColumns(10);
		panel.add(textFieldPregunta);
		
		JPanel panel_2 = new JPanel();
		frame.getContentPane().add(panel_2, BorderLayout.SOUTH);
		
		botonAgregar = new JButton("Agregar");
		panel_2.add(botonAgregar);
		
		botonBuscar = new JButton("Buscar");
		panel_2.add(botonBuscar);
		
		botonActualizar = new JButton("Actualizar");
		panel_2.add(botonActualizar);
		
		botonEliminar = new JButton("Eliminar");
		panel_2.add(botonEliminar);
		
		JPanel panel_3 = new JPanel();
		frame.getContentPane().add(panel_3, BorderLayout.CENTER);
		panel_3.setLayout(new GridLayout(2, 2, 5, 20));
		
		respuesta1 = new JTextField();
		panel_3.add(respuesta1);
		respuesta1.setColumns(10);
		
		respuesta2 = new JTextField();
		panel_3.add(respuesta2);
		respuesta2.setColumns(10);
		
		respuesta3 = new JTextField();
		panel_3.add(respuesta3);
		respuesta3.setColumns(10);
		
		respuesta4 = new JTextField();
		panel_3.add(respuesta4);
		respuesta4.setColumns(10);
		
	}
	
	public JFrame getFrame() {
		return frame;
	}

	public JTextField getTextFieldId() {
		return textFieldId;
	}

	public JTextField getTextFieldPregunta() {
		return textFieldPregunta;
	}

	public JTextField getRespuesta1() {
		return respuesta1;
	}

	public JTextField getRespuesta2() {
		return respuesta2;
	}

	public JTextField getRespuesta3() {
		return respuesta3;
	}

	public JTextField getRespuesta4() {
		return respuesta4;
	}

	public JButton getBotonAgregar() {
		return botonAgregar;
	}

	public JButton getBotonBuscar() {
		return botonBuscar;
	}

	public JButton getBotonActualizar() {
		return botonActualizar;
	}

	public JButton getBotonEliminar() {
		return botonEliminar;
	}

	public void setTextFieldId(JTextField textFieldId) {
		this.textFieldId = textFieldId;
	}

	public void setTextFieldPregunta(JTextField textFieldPregunta) {
		this.textFieldPregunta = textFieldPregunta;
	}

	public void setRespuesta1(JTextField respuesta1) {
		this.respuesta1 = respuesta1;
	}

	public void setRespuesta2(JTextField respuesta2) {
		this.respuesta2 = respuesta2;
	}

	public void setRespuesta3(JTextField respuesta3) {
		this.respuesta3 = respuesta3;
	}

	public void setRespuesta4(JTextField respuesta4) {
		this.respuesta4 = respuesta4;
	}
	
	
}
