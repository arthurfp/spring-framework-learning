package com.arthurfp.hibernatedemo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.arthurfp.hibernatedemo.entity.Course;
import com.arthurfp.hibernatedemo.entity.Instructor;
import com.arthurfp.hibernatedemo.entity.InstructorDetail;

public class EagerLazyDemo {

	public static void main(String[] args) {

		// create session factory
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class).addAnnotatedClass(Course.class).buildSessionFactory();

		// create session
		Session session = factory.getCurrentSession();

		try {

			// start a transaction
			session.beginTransaction();
			
			// get instructor by primary key / id
			int theId = 1;
			Instructor tempInstructor = session.get(Instructor.class, theId);

			System.out.println("Found instructor: " + tempInstructor);
			
			// get courses for the instructor
			System.out.println("Courses: " + tempInstructor.getCourses());

			// commit transaction
			session.getTransaction().commit();
			
			/* -Lazy Loading Issue- */
			
			// if we close the session before the lazy load, it will throw an exception
			// (unless we had loaded data before - called getter method before it closes)
			session.close();
			System.out.println("Courses after close session: " + tempInstructor.getCourses());
			
			/* -- */
			
			System.out.println("Done!");

		} finally {
			factory.close();
			session.close();
		}

	}
}