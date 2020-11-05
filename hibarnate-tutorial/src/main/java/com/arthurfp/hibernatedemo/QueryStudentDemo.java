package com.arthurfp.hibernatedemo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.arthurfp.hibernatedemo.entity.Student;

public class QueryStudentDemo {

	public static void main(String[] args) {

		// create session factory

		// it is not mandatory to use config file name in configure().
		// Hibernate will look for a config file with this default name
		// ("hibernate.cfg.xml")
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Student.class)
				.buildSessionFactory();

		// create session
		Session session = factory.getCurrentSession();

		try {

			// start a transaction
			session.beginTransaction();

			// query students
			List<Student> theStudents = session.createQuery("from Student").getResultList();

			// display the students
			displayStudents(theStudents);

			// query students: lastName='Doe'
			// IMPOTRTANT: notice that we can use lastName (instead of last_name)
			theStudents = session.createQuery("from Student s where s.lastName='Doe'").getResultList();

			// display the students
			System.out.println("\n\nStudens who have last name of Doe");
			displayStudents(theStudents);

			// query students: lastName='Doe' OR firstName='Daffy'
			theStudents = session.createQuery("from Student s where s.lastName='Doe' OR s.firstName='Daffy'")
					.getResultList();
			
			// display the students
			System.out.println("\n\nStudens who have last name of Doe or first name of Daffy");
			displayStudents(theStudents);
			
			// query students where email LIKE '%luv2code.com'
			theStudents = session.createQuery("from Student s where s.email LIKE '%arthurfp.com'")
					.getResultList();
			
			// display the students
			System.out.println("\n\nStudens who have email that ends with arthurfp.com");
			displayStudents(theStudents);

			// commit transaction
			session.getTransaction().commit();

			System.out.println("Done!");

		} finally {
			factory.close();
		}
	}

	private static void displayStudents(List<Student> theStudents) {
		for (Student tempStudent : theStudents) {
			System.out.println(tempStudent);
		}
	}
}