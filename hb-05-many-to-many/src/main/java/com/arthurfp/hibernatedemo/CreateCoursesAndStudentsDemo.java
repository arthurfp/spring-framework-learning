package com.arthurfp.hibernatedemo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.arthurfp.hibernatedemo.entity.Course;
import com.arthurfp.hibernatedemo.entity.Instructor;
import com.arthurfp.hibernatedemo.entity.InstructorDetail;
import com.arthurfp.hibernatedemo.entity.Review;
import com.arthurfp.hibernatedemo.entity.Student;

public class CreateCoursesAndStudentsDemo {

	public static void main(String[] args) {

		// create session factory
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class).addAnnotatedClass(Review.class).addAnnotatedClass(Course.class)
				.addAnnotatedClass(Student.class).buildSessionFactory();

		// create session
		Session session = factory.getCurrentSession();

		try {

			// start a transaction
			session.beginTransaction();

			// create a course
			Course tempCourse = new Course("Pacman - How to Score One Million Points");
			
			// save the course
			System.out.println("Saving Course: " + tempCourse);
			session.save(tempCourse);

			// add some students
			Student tempStudent1 = new Student("John", "Doe", "john@arthurfp.com");
			Student tempStudent2 = new Student("Mary", "Public", "mary@arthurfp.com");
			
			// add students to the course
			tempCourse.addStudent(tempStudent1);
			tempCourse.addStudent(tempStudent2);

			// save the students
			System.out.println("Saving Students ...");
			session.save(tempStudent1);
			session.save(tempStudent2);
			System.out.println("Saved Students: " + tempCourse.getStudents());
			

			// commit transaction
			session.getTransaction().commit();

			System.out.println("Done!");

		} finally {
			factory.close();
			session.close();
		}

	}
}