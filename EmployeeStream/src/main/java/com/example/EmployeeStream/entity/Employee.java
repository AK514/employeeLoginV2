package com.example.EmployeeStream.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
//import jakarta.validation.constraints.Max;
//import jakarta.validation.constraints.Min;

@Entity
@Table(name="Employee_Details_Table")
public class Employee {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int id;
	@NotBlank(message = "First Name shouldn't be null")
	private String firstName;
	@NotBlank(message = "Last Name shouldn't be null")
	private String lastName;

	@Min(18)
	@Max(60)
	private int age;

	@NotBlank
	private String gender;
	@NotNull(message = "salary shouldn't not null ")
	private int salary;
	
	private String department;

	public Employee(String firstName, String lastName, int age, String gender, int salary, String department) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.gender = gender;
		this.salary = salary;
		this.department = department;
	}

	public int getId() {
		return id;
	}
	
	public Employee() {
		
	}
	
	@Override
	public String toString() {
		return "Employee [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", age=" + age
				+ ", gender=" + gender + ", salary=" + salary + ", department=" + department + "]";
	}


	


	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int d) {
		this.salary = d;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public Employee orElse(Object object) {
		// TODO Auto-generated method stub
		return null;
	}
}
