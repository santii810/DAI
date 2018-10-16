package es.uvigo.esei.dai.dojo3.controller;

import java.util.List;

import es.uvigo.esei.dai.dojo3.EmployeeNotFoundException;
import es.uvigo.esei.dai.dojo3.SearchType;
import es.uvigo.esei.dai.dojo3.model.entity.Employee;

public interface EmployeesController {
	public void create(Employee employee);
	public void update(Employee employee) throws EmployeeNotFoundException;
	public void delete(int id) throws EmployeeNotFoundException;
	public Employee get(int id) throws EmployeeNotFoundException;
	
	public List<Employee> list();
	public List<Employee> list(SearchType type, String value)
	throws IllegalArgumentException;
}
