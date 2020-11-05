package com.arthurfp.hibernatedemo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.arthurfp.hibernatedemo.entity.Course;
import com.arthurfp.hibernatedemo.entity.Instructor;
import com.arthurfp.hibernatedemo.entity.InstructorDetail;
import com.arthurfp.hibernatedemo.entity.Review;

public class DeleteCoursesAndReviewsDemo {

	public static void main(String[] args) {

		// create session factory
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class).addAnnotatedClass(Course.class)
				.addAnnotatedClass(Review.class).buildSessionFactory();

		// create session
		Session session = factory.getCurrentSession();

		try {

			// start a transaction
			session.beginTransaction();

			// get the course
			int theId = 10;
			Course tempCourse = session.get(Course.class, theId);

			// print the course
			System.out.println("Found course: " + tempCourse);

			// print the course reviews
			System.out.println("Reviews: " + tempCourse.getReviews());
			
			// delete the course
			session.delete(tempCourse);

			// commit transaction
			session.getTransaction().commit();

			System.out.println("Done!");

		} finally {
			factory.close();
			session.close();
		}

	}
}