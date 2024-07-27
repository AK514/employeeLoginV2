package com.example.EmployeeStream.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.EmployeeStream.entity.Employee;

@Repository
public interface EmployeeDetailsRepository extends JpaRepository<Employee, Integer>{
	
}
