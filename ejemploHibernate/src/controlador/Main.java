package controlador;

import helpers.HibernateHelper;

import org.hibernate.SessionFactory;

import utils.SessionFactoryUtil;
import vista.Interfaz;

public class Main {
	
	public static void main(String[] args) {
		SessionFactory sesion = SessionFactoryUtil.getSessionFactory();
		HibernateHelper helper = new HibernateHelper();
		Interfaz interfaz = new Interfaz();
		interfaz.setVisible(true);
	}
}
