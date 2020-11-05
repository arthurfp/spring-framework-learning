package com.arthurfp.hibernatedemo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import com.arthurfp.hibernatedemo.entity.Course;
import com.arthurfp.hibernatedemo.entity.Instructor;
import com.arthurfp.hibernatedemo.entity.InstructorDetail;

public class FetchJoinDemo {

	public static void main(String[] args) {

		// create session factory
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class).addAnnotatedClass(Course.class).buildSessionFactory();

		// create session
		Session session = factory.getCurrentSession();

		try {

			// start a transaction
			session.beginTransaction();

			/*
			 * -FIX FOR LAZY LOADING ISSUE- -HIBERNATE QUERY WITH HQL-
			 */

			// get instructor by primary key / id
			int theId = 1;
			Query<Instructor> query = session.createQuery(
					"Select i from Instructor i "
					+ "JOIN FETCH i.courses "
					+ "where i.id=:theInstructorId", 
				Instructor.class);
			
			// set theInstructorId parameter on query
			query.setParameter("theInstructorId", theId);

			// execute the query and get the Instructor
			Instructor tempInstructor = query.getSingleResult();
			
			System.out.println("Found instructor: " + tempInstructor);

			// commit transaction
			session.getTransaction().commit();

			/* -Lazy Loading Issue- */

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