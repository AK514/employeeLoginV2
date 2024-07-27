package com.example.EmployeeStream.service;

import java.util.*;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import com.example.EmployeeStream.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.EmployeeStream.entity.Employee;
import com.example.EmployeeStream.repository.EmployeeDetailsRepository;

@Service
public class EmployeeDetailsService {
	
	@Autowired
	public EmployeeDetailsRepository employeeDetailsRepository;
	
	//Add employees list
	public Employee addEmployees(Employee employees){
		return employeeDetailsRepository.save(employees);
	}
	
	//get All employees
	public List<Employee> retrieveAllEmployees(){
		 return employeeDetailsRepository.findAll();
	}
	
	//update salary who employees are having even ids
	public List<Employee> updateEmployees(){
		
	List<Employee> newEmpList = employeeDetailsRepository.findAll().stream()
								.filter(e->e.getId()%2==0)
								.peek(e->e.setSalary(e.getSalary()*2))
								.collect(Collectors.toList());
	
		return employeeDetailsRepository.saveAll(newEmpList);
		
	}




	
	public void updateEmployee(Employee employee) {
		Optional<Employee> emp= employeeDetailsRepository.findById(employee.getId());
		if (emp.isPresent()) {
	        Employee existingEmp = emp.get();
	        existingEmp.setFirstName(employee.getFirstName());
	        existingEmp.setLastName(employee.getLastName());
	        existingEmp.setAge(employee.getAge());
	        existingEmp.setGender(employee.getGender());
	        existingEmp.setSalary(employee.getSalary()); 
	        employeeDetailsRepository.save(existingEmp);
	       
	    } 
	}
	
	//find Number of Male and female Employees using groupingBy() and Collectors.counting()
	public Map<String,Long> noOfMaleAndFemaleEmployees(){
		Map<String,Long> newEmployeeList= employeeDetailsRepository.findAll().stream()
										.collect(Collectors.groupingBy(Employee::getGender,Collectors.counting()));
		
		return newEmployeeList;
	}
	
	//Find Average age of Male and Female Employess using groupingBy() and Collectors.averagingInt()
	public Map<String,Double> avgAgeOfMaleAndFemaleEmployees(){
		 
		return employeeDetailsRepository.findAll().stream()
				.collect(Collectors.groupingBy(Employee::getGender,
						Collectors.averagingInt(Employee::getAge)));
	}
	
	//Delete all Employees
	public String deleteEmployee(int id){
		Employee emp = employeeDetailsRepository.findById(id).get();
		employeeDetailsRepository.delete(emp);
		return "Deleted Successfully";
	}
	
//	Employee e1=new Employee("Arun","kumar",20,"Male",12000,"HR");
//	employeeDetailsRepository.save(e1);
//	
//	List<Employee> employeeList = employeeDetailsRepository.findAll();
	
	//find details of highest paid employee
	public Employee getHighestPaidOfEmployee() {
		Optional<Employee> emp1= employeeDetailsRepository.findAll().stream().collect(Collectors.maxBy(Comparator.comparingInt(Employee :: getSalary)));
		Employee employee=emp1.get();
		return employee;
	}
	
	//find employees who are getting salary less than 25k
	public List<String> getEmployeesSalaryLessThan25k() {
		 employeeDetailsRepository.findAll().stream()
		 							.filter(e->e.getSalary()<30000)
		 							.map(Employee :: getFirstName)
		 							.forEach(System.out :: println);
		 
		 return employeeDetailsRepository.findAll().stream()
			.filter(e->e.getSalary()<30000)
			.map(Employee :: getFirstName).collect(Collectors.toList());
	}
	
	//find no of employees in each department
	public Map<String,Long> getNoOfEmployeesInDepatment(){
		
		Map<String,Long> noOfEmpInDept = employeeDetailsRepository.findAll().stream()
								.collect(Collectors.groupingBy(Employee :: getDepartment,
										Collectors.counting()));
		
		Set<Entry<String,Long>> entrySet = noOfEmpInDept.entrySet();
		
		for(Entry<String,Long> entry : entrySet) {
			System.out.println(entry.getKey()+ " = "+entry.getValue());
		}
		
		return noOfEmpInDept;
	}
	
	//find the details of youngest employee 
	public Employee getYoungestMaleEmployee() {
		Optional<Employee> emp1= employeeDetailsRepository.findAll().stream()
						.filter(e->"Male".equals(e.getGender())  )
						.min(Comparator.comparingInt(Employee::getAge));
		Employee employee =emp1.get();
		//System.out.println(employee);
		return employee;
	}
	
	// list of employees based on departmentnames
	public Map<String,List<Employee>> getAllEmployeesInEachDepartments(){
		
		return employeeDetailsRepository.findAll().stream()
										.collect(Collectors.groupingBy(Employee :: getDepartment));
	}
	
	//find total organization salary
	public void employeeSalaryStatistics(){
		IntSummaryStatistics emp1=employeeDetailsRepository.findAll().stream()
							.collect(Collectors.summarizingInt(Employee :: getSalary));
		System.out.println(emp1.getSum());
		System.out.println(emp1.getAverage());
		
	}


	public Employee getEmployeeById(int id) throws UserNotFoundException {
		Optional<Employee> emp = employeeDetailsRepository.findById(id);
		return emp.orElseThrow(() -> new UserNotFoundException("Employee not found with ID: " + id));
	}
}
