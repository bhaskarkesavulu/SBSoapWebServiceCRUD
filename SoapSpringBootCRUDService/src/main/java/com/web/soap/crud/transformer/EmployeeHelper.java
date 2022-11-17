package com.web.soap.crud.transformer;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.web.crud.soap.stub.AddEmployeeRequest;
import com.web.soap.crud.entities.Employee;

@Component
public class EmployeeHelper {

	public Employee prepareEmployeeModel(AddEmployeeRequest request) {

		Employee employee = new Employee();
		BeanUtils.copyProperties(request.getEmployeeInfo(), employee);
		return employee;
	}

}
