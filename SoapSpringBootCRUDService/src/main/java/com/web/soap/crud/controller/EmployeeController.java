package com.web.soap.crud.controller;


import org.hibernate.tool.schema.extract.spi.InformationExtractor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;

import com.web.crud.soap.stub.AddEmployeeRequest;
import com.web.crud.soap.stub.AddEmployeeResponse;
import com.web.crud.soap.stub.DeleteEmployeeRequest;
import com.web.crud.soap.stub.DeleteEmployeeResponse;
import com.web.crud.soap.stub.EmployeeInfo;
import com.web.crud.soap.stub.GetEmployeeByIdRequest;
import com.web.crud.soap.stub.GetEmployeeResponse;
import com.web.crud.soap.stub.ServiceStatus;
import com.web.crud.soap.stub.UpdateEmployeeResponse;
import com.web.soap.crud.entities.Employee;
import com.web.soap.crud.service.EmployeeService;

@EnableWs
//@RequestMapping("/api")
@RestController
public class EmployeeController extends WebServiceGatewaySupport{

	@Autowired
	private EmployeeService service;

	/*
	 * @GetMapping(value = "/crud/{employeeId}", produces =
	 * MediaType.APPLICATION_JSON_VALUE, headers = "Accept=application/json") public
	 * GetEmployeeResponse getEmployeeById(@PathVariable("employeeId") long
	 * employeeId) {
	 * 
	 * GetEmployeeResponse response = new GetEmployeeResponse(); EmployeeInfo info =
	 * new EmployeeInfo(); GetEmployeeByIdRequest request = new
	 * GetEmployeeByIdRequest(); request.setEmployeeId(employeeId); Employee emp =
	 * service.getEmployeeById(employeeId); System.out.println("Employee is : " +
	 * emp); info.setEmployeeId(employeeId); info.setAddress(emp.getAddress());
	 * info.setDepartment(emp.getDepartment()); info.setName(emp.getName());
	 * info.setPhone(emp.getPhone()); response.setEmployeeInfo(info); return
	 * response; }
	 */
	
	//@PostMapping(value = "/crudservice", produces = MediaType.APPLICATION_JSON_VALUE, headers = "Accept=application/json")
	@RequestMapping(value = "/crudservice1", method = RequestMethod.POST, headers = "Accept=*/*")
	public GetEmployeeResponse getEmployeeById(@RequestBody GetEmployeeByIdRequest request) {
		
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
	
	@RequestMapping(value = "/crudservice1/save", method = RequestMethod.POST, headers = "Accept=*/*")
	public AddEmployeeResponse addEmployee(@RequestBody Employee employee) {
		
		AddEmployeeResponse response = new AddEmployeeResponse();
		ServiceStatus serviceStatus = new ServiceStatus();
		EmployeeInfo info = new EmployeeInfo();
		service.addEmployee(employee);
		info.setEmployeeId(employee.getEmployeeId());
		info.setAddress(employee.getAddress());
		info.setDepartment(employee.getDepartment());
		info.setName(employee.getName());
		info.setPhone(employee.getPhone());
		response.setEmployeeInfo(info);
		serviceStatus.setStatus("SUCCESS");
		serviceStatus.setMessage("Content Added Successfully");
		response.setServiceStatus(serviceStatus);
		return response;		
	}
	
	@RequestMapping(value = "/crudservice1/update", method = RequestMethod.PUT, headers = "Accept=*/*")
	public UpdateEmployeeResponse updateEmployee(@RequestBody Employee employee) {
		
		UpdateEmployeeResponse response = new UpdateEmployeeResponse();
		ServiceStatus serviceStatus = new ServiceStatus();
		EmployeeInfo info = new EmployeeInfo();
		service.updateEmployee(employee);
		info.setEmployeeId(employee.getEmployeeId());
		info.setAddress(employee.getAddress());
		info.setDepartment(employee.getDepartment());
		info.setName(employee.getName());
		info.setPhone(employee.getPhone());
		serviceStatus.setStatus("SUCCESS");
		serviceStatus.setMessage("Content Added Successfully");
		response.setServiceStatus(serviceStatus);
		return response;		
	}
	
	@RequestMapping(value = "/crudservice1/delete", method = RequestMethod.DELETE, headers = "Accept=*/*")
	public DeleteEmployeeResponse deleteEmployee(@RequestBody DeleteEmployeeRequest request) {
		
		service.deleteEmployee(request.getEmployeeId());
		ServiceStatus status = new ServiceStatus();
		status.setStatus("SUCCESS");
		status.setMessage("Content Deleted Successfully");
		DeleteEmployeeResponse response = new DeleteEmployeeResponse();
		response.setServiceStatus(status);
		return response;
	}
}
