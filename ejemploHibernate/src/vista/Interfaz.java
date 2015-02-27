package vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;

import java.awt.GridLayout;

import javax.swing.JButton;

import controlador.MiActionListener;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Interfaz extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField txtRespuesta_2;
	private JTextField txtRespuesta;
	private JTextField txtRespuesta_1;
	private JTextField txtRespuesta_3;

	/**
	 * Create the frame.
	 */
	public Interfaz() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);
		
		JLabel lblIntroduzcaLaPregunta = new JLabel("Introduzca la pregunta:");
		panel.add(lblIntroduzcaLaPregunta);
		
		textField = new JTextField();
		panel.add(textField);
		textField.setColumns(10);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new GridLayout(2, 2, 5, 20));
		
		txtRespuesta = new JTextField();
		panel_1.add(txtRespuesta);
		txtRespuesta.setColumns(10);
		
		txtRespuesta_1 = new JTextField();
		panel_1.add(txtRespuesta_1);
		txtRespuesta_1.setColumns(10);
		
		txtRespuesta_2 = new JTextField();
		panel_1.add(txtRespuesta_2);
		txtRespuesta_2.setColumns(10);
		
		txtRespuesta_3 = new JTextField();
		panel_1.add(txtRespuesta_3);
		txtRespuesta_3.setColumns(10);
		
		JButton btnNewButton = new JButton("Agregar");
		contentPane.add(btnNewButton, BorderLayout.SOUTH);
		MiActionListener mal = new MiActionListener(textField,txtRespuesta,
				txtRespuesta_1,txtRespuesta_2,txtRespuesta_3);
		btnNewButton.addActionListener(mal);
	}

}
