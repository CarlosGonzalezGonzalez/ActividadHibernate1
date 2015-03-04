package controlador;

import helpers.HibernateHelper;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;

import javax.swing.JOptionPane;

import model.Answers;
import model.Questions;
import vista.Interfaz;

public class Controlador {
	private final static String SENTENCIA_LAST_ID = "SELECT TOP 1 _id FROM questions ORDER BY _id DESC";
	private final static String CASO_ADD = "Agregar";
	private final static String CASO_BUSCAR = "Buscar";
	private final static String CASO_BORRAR = "Borrar";
	private final static String CASO_ACTUALIZAR = "Actualziar";

	HibernateHelper helper;

	Interfaz view;

	private String txtP;
	private String txtR1;
	private String txtR2;
	private String txtR3;
	private String txtR4;
	private int idBuscar;

	private int id;
	private HashSet<Answers> respuestas = null;

	public Controlador() {
		respuestas = new HashSet<>(0);
		helper = new HibernateHelper();
		view = new Interfaz();
		view.getFrame().setVisible(true);

		view.getBotonAgregar().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				añadir();
			}
		});

		view.getBotonBuscar().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buscar();
			}
		});

		view.getBotonActualizar().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actualizar();
			}
		});
		
		view.getBotonEliminar().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				eliminar();
			}
		});

	}

	public void añadir() {
		// Recogemos los datos introducidos por el usuario
		recogerDatos();

		// Cogemos el id de la ultima pregunta y le añadimos uno para crear una
		// pregunta nueva
		añadirPregunta();

		// Array con los textos de las respuestas a la pregunta
		String[] arrayTxtRespuestas = new String[4];
		arrayTxtRespuestas[0] = txtR1;
		arrayTxtRespuestas[1] = txtR2;
		arrayTxtRespuestas[2] = txtR3;
		arrayTxtRespuestas[3] = txtR4;

		añadirRespuestas(arrayTxtRespuestas);

		JOptionPane.showMessageDialog(view.getFrame(), "Añadida");
	}

	public void buscar() {
		try {
			idBuscar = Integer.parseInt(view.getTextFieldId().getText());

			Questions question = helper.getQuestion(idBuscar);

			if (question != null) {

				ArrayList<Answers> arrayRespuestas = obtenerRespuestas(question);

				// Mostamos los datos
				mostarDatos(question, arrayRespuestas);

			} else {
				JOptionPane.showMessageDialog(view.getFrame(),
						"No ha ninguna pregunta con ese id");
			}
		} catch (NumberFormatException nfe) {
			JOptionPane.showMessageDialog(view.getFrame(),
					"El id es un campo numerico");
		}
	}

	public void actualizar() {
		try {
			idBuscar = Integer.parseInt(view.getTextFieldId().getText());
			recogerDatos();
			
			helper.update(idBuscar,txtP,txtR1,txtR2,txtR3,txtR4);
			
			JOptionPane.showMessageDialog(view.getFrame(),
					"Cambios realizados correctamente");
		} catch (NumberFormatException nfe) {
			JOptionPane.showMessageDialog(view.getFrame(),
					"El id es un campo numerico");
		}

	}
	
	public void eliminar(){
		try{
			idBuscar = Integer.parseInt(view.getTextFieldId().getText());
			
			if(helper.delete(idBuscar)){
				JOptionPane.showMessageDialog(view.getFrame(),
						"Datos eliminados correctamente");
			}else{
				JOptionPane.showMessageDialog(view.getFrame(),
						"Introduzca un id valido");
			}
		} catch (NumberFormatException nfe) {
			JOptionPane.showMessageDialog(view.getFrame(),
					"El id es un campo numerico");
		}
	}

	private void añadirRespuestas(String[] arrayTxtRespuestas) {
		for (int i = 0; i < 4; i++) {
			try {
				id = helper.getLastAnswer().getId() + 1;
			} catch (NullPointerException npe) {
				id = 1;
			}
			helper.addAnswer(id, arrayTxtRespuestas[i], respuestas);
		}

		helper.getLastQuestion().setAnswerses(respuestas);
	}

	private void añadirPregunta() {
		try {
			id = helper.getLastQuestion().getId() + 1;
		} catch (NullPointerException npe) {
			id = 1;
		}

		helper.addQuestion(id, txtP, respuestas);
	}

	private void recogerDatos() {
		this.txtP = view.getTextFieldPregunta().getText();
		this.txtR1 = view.getRespuesta1().getText();
		this.txtR2 = view.getRespuesta2().getText();
		this.txtR3 = view.getRespuesta3().getText();
		this.txtR4 = view.getRespuesta4().getText();
	}

	private ArrayList<Answers> obtenerRespuestas(Questions question) {
		ArrayList<Answers> arrayRespuestas = new ArrayList();

		for (Answers a : question.getAnswerses()) {
			arrayRespuestas.add(a);
		}

		Collections.sort(arrayRespuestas, new Comparator<Answers>() {
			@Override
			public int compare(Answers a, Answers a2) {
				return a.getId() - a2.getId();
			}
		});
		return arrayRespuestas;
	}

	private void mostarDatos(Questions question,
			ArrayList<Answers> arrayRespuestas) {
		view.getTextFieldPregunta().setText(question.getText());
		view.getRespuesta1().setText(arrayRespuestas.get(0).getText());
		view.getRespuesta2().setText(arrayRespuestas.get(1).getText());
		view.getRespuesta3().setText(arrayRespuestas.get(2).getText());
		view.getRespuesta4().setText(arrayRespuestas.get(3).getText());
	}
}
