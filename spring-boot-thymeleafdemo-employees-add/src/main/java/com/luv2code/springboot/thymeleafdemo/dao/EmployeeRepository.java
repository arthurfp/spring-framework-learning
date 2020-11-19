package com.arthurfp.springboot.thymeleafdemo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.arthurfp.springboot.thymeleafdemo.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

	// that's it ... no need to write any code LOL!
	
	// add a method to sort by last name - JPA will automatically generate method based on method name pattern
	public List<Employee> findAllByOrderByLastNameAsc();
	
}
