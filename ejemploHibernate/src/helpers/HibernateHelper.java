package helpers;

import java.util.HashSet;
import java.util.Set;

import javax.swing.JOptionPane;

import model.Answers;
import model.Questions;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import utils.SessionFactoryUtil;

public class HibernateHelper {
	
	private SessionFactory sesion;
	
	public HibernateHelper(){
		sesion = SessionFactoryUtil.getSessionFactory();
	}
	
	public void addQuestion(int id,String text,HashSet<Answers> respuestas){
		Session session = sesion.openSession();
		Transaction tx = session.beginTransaction();
		
		Questions question = new Questions(id,text,respuestas);
		session.save(question);

		tx.commit();
		session.close();
	}
	
	public void addAnswer(int id,String txtRespuesta,HashSet<Answers> respuestas){
		Session session = sesion.openSession();
		Transaction tx = session.beginTransaction();
		
		Answers respuesta = new Answers(id,getLastQuestion(),txtRespuesta);
		respuestas.add(respuesta);
		session.save(respuesta);
		
		tx.commit();
		session.close();
	}
	
	public Questions getLastQuestion(){
		Session session = sesion.openSession();
		
		Query query = session.createQuery("from Questions order by id DESC");
		query.setMaxResults(1);
		Questions last = (Questions) query.uniqueResult();
		
		session.close();
		return last;
	}
	public Answers getLastAnswer(){
		Session session = sesion.openSession();
		
		Query query = session.createQuery("from Answers order by id DESC");
		query.setMaxResults(1);
		Answers last = (Answers) query.uniqueResult();
		
		session.close();
		return last;
	}
	
	public Questions getQuestion(int id){
		Session session = sesion.openSession();
		
		Questions q = (Questions) session.get(Questions.class,id);
		
		Set<Answers>sRespuestas = new HashSet<Answers>(0);
		for(Answers a : q.getAnswerses()){
			sRespuestas.add(a);
		}
		
		session.close();
		q.setAnswerses(sRespuestas);
		
		return q;
	}
	
	public void update(int id,String pregunta,String r1,String r2,String r3,String r4){
		String[] arrayRespuestas = {r1,r2,r3,r4};
		int i = 0;
		
		Session session = sesion.openSession();
		Transaction tx = session.beginTransaction();
		
		// Obtenemos la pregunta con el id que ha introducido el usuario
		Questions q = (Questions) session.get(Questions.class,id);
		
		// Comparamos los textos de las preguntas
		if(!pregunta.equals("")){
			if(!q.getText().equalsIgnoreCase(pregunta)){
				q.setText(pregunta);
			}
		}
		// Comparamos los textos de las respuestas
		for(Answers a : q.getAnswerses()){
			if(!arrayRespuestas[i].equals("")){
				if(!a.getText().equalsIgnoreCase(arrayRespuestas[i])){
					a.setText(arrayRespuestas[i]);
				}
			}
			i++;
		}
		
		tx.commit();
		session.close();
	}
	
	public boolean delete(int id){
		int lastId = getLastQuestion().getId();
		
		if(id <= lastId){
			Session session = sesion.openSession();
			Transaction tx = session.beginTransaction();
			
			Query query = session.createQuery("delete Answers where idpregunta = " + id);
			int result = query.executeUpdate();
			
			query = session.createQuery("delete Questions where id = " + id);
			result = query.executeUpdate();
			
			tx.commit();
			session.close();
			
			return true;
		}else{
			return false;
		}
	}
}
