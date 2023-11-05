package com.example.employeecrud.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.employeecrud.model.Employee;
import com.example.employeecrud.repository.EmployeeRepository;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepo;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	public String addEmployee(Employee employee) {
		employee.setPassword(bCryptPasswordEncoder.encode(employee.getPassword()));
		employeeRepo.save(employee).toString();
		return "Employee added successfully";
	}

	public Optional<Employee> findEmployeeById(int id) {
		return employeeRepo.findById(id);
	}

	public List<Employee> getAllEmployee() {
		return employeeRepo.findAll();
	}

	public String updateEmployee(Employee employee, int id) {
		Optional<Employee> emp = findEmployeeById(id);
		if (emp.isPresent()) {
			Employee empl = emp.get();
			empl.setEmailAddress(employee.getEmailAddress());
			empl.setFirstName(employee.getFirstName());
			empl.setLastName(employee.getLastName());
			empl.setPassword(employee.getPassword());
			empl.setRoles(employee.getRoles());
			return "Employee Updated Successfully";
		} else {
			return "Employee Not Found";
		}

	}
}
