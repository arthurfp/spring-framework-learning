package com.arthurfp.hibernatedemo.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "course_id")
	private List<Review> reviews;
	
	// define constructors
	public Course() {
		
	}

	public Course(String title) {
		this.title = title;
	}
	
	// add convenience method
	public void addReview(Review tempReview) {
		
		if (reviews == null) {
			reviews = new ArrayList<>();
		}
		
		reviews.add(tempReview);
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

	public List<Review> getReviews() {
		return reviews;
	}

	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}

	// toString() method
	@Override
	public String toString() {
		return "Course [id=" + id + ", title=" + title + "]";
	}
}