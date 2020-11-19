package com.arthurfp.springboot.cruddemo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.arthurfp.springboot.cruddemo.entity.Employee;


// BY DEFAULT - Spring Data REST will get class type with first letter in lowercase and add an "s" to it's end for endpoints: "/employees"

// However you can specify you custom endpoint name with the below annotation

@RepositoryRestResource(path="members")
public interface EmployeeRepository extends JpaRepository<Employee, Integer> { // class, id
	
	// Spring Data REST will get class type with first letter in lowercase and add an "s" to it's end for endpoints: "/employees"
	
	// that's it ... no need to write any code!
	

}
