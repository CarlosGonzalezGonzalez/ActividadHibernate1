package helpers;

import java.util.HashSet;

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
}
