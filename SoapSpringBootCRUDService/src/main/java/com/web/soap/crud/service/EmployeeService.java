package com.web.soap.crud.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.soap.crud.entities.Employee;
import com.web.soap.crud.repo.EmployeeRepository;
import com.web.soap.crud.transformer.EmployeeHelper;

@Service
public class EmployeeService implements IEmployeeService {

	@Autowired
	EmployeeRepository employeeRepository;

	@Autowired
	EmployeeHelper employeeHelper;

	@Override
	public Employee getEmployeeById(Long employeeId) {

		Employee obj = employeeRepository.findByEmployeeId(employeeId);

		return obj;
	}

	@Override
	public void addEmployee(Employee employee) {

		employeeRepository.save(employee);
	}

	@Override
	public void updateEmployee(Employee employee) {

		employeeRepository.save(employee);
	}

	@Override
	public void deleteEmployee(long employeeId) {

		employeeRepository.deleteById(employeeId);
	}


}
