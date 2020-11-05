package com.arthurfp.hibernatedemo.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

// annotate the class as an entity and map to db table
@Entity
@Table(name="course")
public class Course {
	
	// define the fields and annotate them with column names
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="title")
	private String title;
	
	// annotate with @ManyToOne and set cascade to don't delete on cascade
	@ManyToOne(cascade = { CascadeType.DETACH, 
			CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH })
	@JoinColumn(name="instructor_id")
	private Instructor instructor;
	
	// define constructors
	public Course() {
		
	}

	public Course(String title) {
		this.title = title;
	}

	// getters and setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Instructor getInstructor() {
		return instructor;
	}

	public void setInstructor(Instructor instructor) {
		this.instructor = instructor;
	}

	// toString() method
	@Override
	public String toString() {
		return "Course [id=" + id + ", title=" + title + "]";
	}
}