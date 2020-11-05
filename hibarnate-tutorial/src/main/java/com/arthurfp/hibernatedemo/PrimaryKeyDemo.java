package com.arthurfp.hibernatedemo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.arthurfp.hibernatedemo.entity.Student;

public class PrimaryKeyDemo {

	public static void main(String[] args) {
		
		// create session factory
		
		// it is not mandatory to use config file name in configure().
		// Hibernate will look for a config file with this default name ("hibernate.cfg.xml")
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Student.class)
								.buildSessionFactory();
		
		// create session
		
		Session session = factory.getCurrentSession();
		
		try {
			
			// create 3 student object
			System.out.println("Creating 3 student objects...");
			Student tempStudent1 = new Student("John", "Doe", "john@arthurfp.com");
			Student tempStudent2 = new Student("Mary", "Public", "mary@arthurfp.com");
			Student tempStudent3 = new Student("Bonita", "Applebum", "bonita@arthurfp.com");
			
			// start a transaction
			session.beginTransaction();
			
			// save the student objects
			session.save(tempStudent1);
			session.save(tempStudent2);
			session.save(tempStudent3);
			
			// commit transaction
			session.getTransaction().commit();
			
			System.out.println("Done!");
			
		} finally {
			factory.close();
		}
	}
}