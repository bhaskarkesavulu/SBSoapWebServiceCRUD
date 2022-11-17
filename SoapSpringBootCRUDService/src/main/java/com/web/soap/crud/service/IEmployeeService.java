package com.web.soap.crud.service;

import com.web.soap.crud.entities.Employee;

public interface IEmployeeService {

	void updateEmployee(Employee employee);

	void deleteEmployee(long employeeId);

	Employee getEmployeeById(Long employeeId);

	void addEmployee(Employee employee);
	
}
