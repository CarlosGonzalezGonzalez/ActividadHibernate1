package controlador;

import helpers.HibernateHelper;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;

import javax.swing.JTextField;

import model.Answers;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import utils.SessionFactoryUtil;

public class MiActionListener implements ActionListener {
	private final static String SENTENCIA_LAST_ID = "SELECT TOP 1 _id FROM questions ORDER BY _id DESC";

	private JTextField pregunta;
	private JTextField r1;
	private JTextField r2;
	private JTextField r3;
	private JTextField r4;

	private String txtP;
	private String txtR1;
	private String txtR2;
	private String txtR3;
	private String txtR4;

	private int id;
	private HashSet<Answers> respuestas = null;

	public MiActionListener(JTextField pregunta, JTextField res1,
			JTextField res2, JTextField res3, JTextField res4) {
		this.pregunta = pregunta;
		this.r1 = res1;
		this.r2 = res2;
		this.r3 = res3;
		this.r4 = res4;
		this.respuestas = new HashSet();
		this.id = 0;
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		// Recogemos los datos introducidos por el usuario
		this.txtP = pregunta.getText();
		this.txtR1 = r1.getText();
		this.txtR2 = r2.getText();
		this.txtR3 = r3.getText();
		this.txtR4 = r4.getText();

		HibernateHelper helper = new HibernateHelper();
		// Cogemos el id de la ultima pregunta y le añadimos uno para crear una
		// pregunta nueva
		try {
			id = helper.getLastQuestion().getId() + 1;
		} catch (NullPointerException npe) {
			id = 1;
		}

		helper.addQuestion(id, txtP, respuestas);

		// Array con los textos de las respuestas a la pregunta
		String[] arrayTxtRespuestas = new String[4];
		arrayTxtRespuestas[0] = txtR1;
		arrayTxtRespuestas[1] = txtR2;
		arrayTxtRespuestas[2] = txtR3;
		arrayTxtRespuestas[3] = txtR4;

		for (int i = 0; i < 4; i++) {
			helper.addAnswer(i, arrayTxtRespuestas[i], respuestas);
		}

		helper.getLastQuestion().setAnswerses(respuestas);

	}

	public void conectarBD(Connection conexion) {
		try {
			conexion = DriverManager.getConnection(
					"jdbc:mysql://localhost/QUIZIT", "QUIZIT", "QUIZIT");
			Class.forName("com.mysql.jdbc.Driver");
		} catch (SQLException e) {
			System.err.println("Error SQL al conectar a la BD");
			System.err.println(e.getMessage());
		} catch (ClassNotFoundException e) {
			System.err.println("Clase no encontrada al conectar a la BD");
		}
	}
}
