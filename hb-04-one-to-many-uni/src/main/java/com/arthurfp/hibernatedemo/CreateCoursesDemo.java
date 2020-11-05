package com.arthurfp.hibernatedemo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.arthurfp.hibernatedemo.entity.Course;
import com.arthurfp.hibernatedemo.entity.Instructor;
import com.arthurfp.hibernatedemo.entity.InstructorDetail;

public class CreateCoursesDemo {

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
			
			// create some courses
			Course tempCourse1 = new Course("Air Guitar - The Ultimate Guide");
			Course tempCourse2 = new Course("The Pinball Masterclass");
			
			// add courses to instructor
			tempInstructor.add(tempCourse1);
			tempInstructor.add(tempCourse2);

			// save the courses
			System.out.println("Saving Course: " + tempCourse1);
			session.save(tempCourse1);
			System.out.println("Saving Course: " + tempCourse2);
			session.save(tempCourse2);

			// commit transaction
			session.getTransaction().commit();

			System.out.println("Done!");

		} finally {
			factory.close();
			session.close();
		}

	}
}