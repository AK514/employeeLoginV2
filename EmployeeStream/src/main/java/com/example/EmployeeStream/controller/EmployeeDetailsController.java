package com.example.EmployeeStream.controller;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import com.example.EmployeeStream.exception.UserNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.EmployeeStream.entity.Employee;
import com.example.EmployeeStream.service.EmployeeDetailsService;

@CrossOrigin(origins = "http://localhost:4200,http://localhost:52849")

@RestController
@RequestMapping("/employeeDetails")
public class EmployeeDetailsController {
	
	@Autowired
	private EmployeeDetailsService employeeDetailService;
	
	@GetMapping("/fetch")
	public List<Employee> fetchEmployees(){
		return employeeDetailService.retrieveAllEmployees();
	}
	
	@PostMapping("/save")
	public Employee addEmployees(@RequestBody @Valid Employee employees){
		return employeeDetailService.addEmployees(employees);
	}
	

	
	@PutMapping("/updateEmployee")
	public void updateEmployee(@RequestBody Employee employee){
		
		employeeDetailService.updateEmployee(employee);
	}
	
	@DeleteMapping("/deleteEmployee/{id}")
	public String deleteEmpList(@PathVariable int id){
		
		return employeeDetailService.deleteEmployee(id);
	}

	/*@GetMapping("/getEmployeeById/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable int id) {
		try {
			Employee employee = employeeDetailService.getEmployeeById(id);
			return new ResponseEntity<>(employee, HttpStatus.OK);
		} catch (UserNotFoundException e) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

		}
	}*/

	@GetMapping("/getEmployeeById/{id}")
	public Employee getEmployeeById(@PathVariable int id) throws UserNotFoundException {
		return employeeDetailService.getEmployeeById(id);
	}

	@PutMapping("/updateSalaries")
	public List<Employee> updateEmployeeSalaries(){

		return employeeDetailService.updateEmployees();
	}
	
	@GetMapping("/getCountOfMaleFemaleEmployees")
	public Map<String,Long> getCountOfMaleFemaleEmployees(){
		return employeeDetailService.noOfMaleAndFemaleEmployees();
	}
	
	@GetMapping("/getAvgAgeOfMaleAndFemaleEmployees")
	public Map<String,Double> getAvgAgeOfMaleAndFemaleEmployees(){
		return employeeDetailService.avgAgeOfMaleAndFemaleEmployees();
	}
	
	@GetMapping("/getHighestPaidEmployee")
	public Employee getHighestPaidEmployee() {
		return employeeDetailService.getHighestPaidOfEmployee();
	}
	
	@GetMapping("/getEmployeesSalaryLessThan25k")
	public List<String> getEmployeesSalaryLessThan25k() {
		return employeeDetailService.getEmployeesSalaryLessThan25k();
	}
	
	@GetMapping("/getNoOfEmployeesInDepartment")
	public Map<String,Long> getNoOfEmployeesByDepartment(){
		return employeeDetailService.getNoOfEmployeesInDepatment();
	}
	
	@GetMapping("/getYoungestMaleEmployee")
	public Employee getYoungestMaleEmployee() {
		return employeeDetailService.getYoungestMaleEmployee();
	}
	
	@GetMapping("/getAllEmployeesInEachDepartments")
	public Map<String,List<Employee>> getAllEmployeesInEachDepartments(){
		return employeeDetailService.getAllEmployeesInEachDepartments();
	}
	
	@GetMapping("/getStatistics")
	public void getStatisticsOfOrganization() {
		employeeDetailService.employeeSalaryStatistics();
	}
}
