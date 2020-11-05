package com.arthurfp.hibernatedemo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.arthurfp.hibernatedemo.entity.Student;

public class DeleteStudentDemo {

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
			int studentId = 1;

			// now get a new session and start transaction
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			// retrieve student based on the id: primary key
			System.out.println("\nGetting student whit id: " + studentId);
			
			Student myStudent = session.get(Student.class, studentId);
			
			// deleting retrieved object
			System.out.println("Delete student...");
			session.delete(myStudent);
			
			// commit transaction
			session.getTransaction().commit();
			
			// now get a new session and start transaction
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			// delete student with id = 2
			System.out.println("Deleting student without retrieving object");
			session.createQuery("delete from Student where id=2").executeUpdate();

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