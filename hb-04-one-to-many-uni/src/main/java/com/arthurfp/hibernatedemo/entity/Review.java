package com.arthurfp.hibernatedemo.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

// annotate the class as an entity and map to db table
@Entity
@Table(name="review")
public class Review {
	
	// define the fields and annotate them with column names
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="comment")
	private String comment;
	
	// define constructors
	public Review() {
		
	}
	
	public Review(String comment) {
		this.comment = comment;
	}

	// getters and setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	// toString() method
	@Override
	public String toString() {
		return "Review [id=" + id + ", comment=" + comment + "]";
	}
	
}