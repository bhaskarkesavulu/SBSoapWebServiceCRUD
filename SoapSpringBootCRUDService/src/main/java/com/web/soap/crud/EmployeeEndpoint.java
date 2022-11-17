package com.web.soap.crud;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.web.crud.soap.stub.AddEmployeeRequest;
import com.web.crud.soap.stub.AddEmployeeResponse;
import com.web.crud.soap.stub.DeleteEmployeeRequest;
import com.web.crud.soap.stub.DeleteEmployeeResponse;
import com.web.crud.soap.stub.EmployeeInfo;
import com.web.crud.soap.stub.GetEmployeeByIdRequest;
import com.web.crud.soap.stub.GetEmployeeResponse;
import com.web.crud.soap.stub.ServiceStatus;
import com.web.crud.soap.stub.UpdateEmployeeRequest;
import com.web.crud.soap.stub.UpdateEmployeeResponse;
import com.web.soap.crud.entities.Employee;
import com.web.soap.crud.service.EmployeeService;

@Endpoint
public class EmployeeEndpoint {
	
	private static final String NAMESPACE_URI = "http://www.com.web.soap.crud/employ-information";
	
	@Autowired
	private EmployeeService service;
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "addEmployeeRequest")
	@ResponsePayload
	public AddEmployeeResponse addEmployee(@RequestPayload AddEmployeeRequest request) {
		
		AddEmployeeResponse response = new AddEmployeeResponse();
		ServiceStatus serviceStatus = new ServiceStatus();
		Employee employee = new Employee();
		BeanUtils.copyProperties(request.getEmployeeInfo(), employee);
		service.addEmployee(employee);
		serviceStatus.setStatus("SUCCESS");
		serviceStatus.setMessage("Content Added Successfully");
		response.setServiceStatus(serviceStatus);
		return response;
	}
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getEmployeeByIdRequest")
	@ResponsePayload
	public GetEmployeeResponse getEmployee(@RequestPayload GetEmployeeByIdRequest request) {
		
		GetEmployeeResponse response = new GetEmployeeResponse();
		EmployeeInfo employeeInfo = new EmployeeInfo();
		BeanUtils.copyProperties(service.getEmployeeById(request.getEmployeeId()), employeeInfo);
		response.setEmployeeInfo(employeeInfo);
		return response;
	}
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "updateEmployeeRequest")
	@ResponsePayload
	public UpdateEmployeeResponse updateEmployee(@RequestPayload UpdateEmployeeRequest request) {
		
		Employee employee = new Employee();
		BeanUtils.copyProperties(request.getEmployeeInfo(), employee);
		service.updateEmployee(employee);
		ServiceStatus status = new ServiceStatus();
		status.setStatus("SUCCESS");
		status.setMessage("Content Updated Successfully");
		UpdateEmployeeResponse response = new UpdateEmployeeResponse();
		response.setServiceStatus(status);
		return response;
	}
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "deleteEmployeeRequest")
	@ResponsePayload
	public DeleteEmployeeResponse deleteEmployee(@RequestPayload DeleteEmployeeRequest request) {
		
		service.deleteEmployee(request.getEmployeeId());
		ServiceStatus status = new ServiceStatus();
		status.setStatus("SUCCESS");
		status.setMessage("Content Deleted Successfully");
		DeleteEmployeeResponse response = new DeleteEmployeeResponse();
		response.setServiceStatus(status);
		return response;
		
	}
	

}
