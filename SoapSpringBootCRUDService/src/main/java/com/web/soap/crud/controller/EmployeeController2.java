package com.web.soap.crud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.web.crud.soap.stub.EmployeeInfo;
import com.web.crud.soap.stub.GetEmployeeByIdRequest;
import com.web.crud.soap.stub.GetEmployeeResponse;
import com.web.soap.crud.entities.Employee;
import com.web.soap.crud.service.EmployeeService;

@EnableWs
@RestController
//@Endpoint

public class EmployeeController2 extends WebServiceGatewaySupport{
	
	private static final String NAMESPACE_URI = "http://www.com.web.soap.crud/employ-information";
	
	@Autowired
	private EmployeeService service;
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getEmployeeByIdRequest")
	@ResponsePayload
	@RequestMapping(value = "/crudservice2", method = RequestMethod.POST, headers = "Accept=*/*")
	public GetEmployeeResponse getEmployeeById(@RequestPayload @RequestBody GetEmployeeByIdRequest request) {
		
		GetEmployeeResponse response = new GetEmployeeResponse();
		EmployeeInfo info = new EmployeeInfo();
		//request.setEmployeeId(employeeId);
		Employee emp = service.getEmployeeById(request.getEmployeeId());
		System.out.println("Employee is : " + emp);
		info.setEmployeeId(emp.getEmployeeId());
		info.setAddress(emp.getAddress());
		info.setDepartment(emp.getDepartment());
		info.setName(emp.getName());
		info.setPhone(emp.getPhone());
		response.setEmployeeInfo(info);
		return response;
	}

}
