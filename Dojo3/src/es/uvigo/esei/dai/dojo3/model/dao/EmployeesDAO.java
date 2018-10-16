package es.uvigo.esei.dai.dojo3.model.dao;

import java.util.List;

import es.uvigo.esei.dai.dojo3.EmployeeNotFoundException;
import es.uvigo.esei.dai.dojo3.model.entity.Employee;

public interface EmployeesDAO {
	public void create(Employee employee); // Debe asignársele a employee el id generado
	public void update(Employee employee) throws EmployeeNotFoundException;
	public void delete(int id) throws EmployeeNotFoundException;
	public Employee get(int id) throws EmployeeNotFoundException;
	
	public List<Employee> list();
	public List<Employee> listByName(String name);
	public List<Employee> listBySurname(String surname);
	public List<Employee> listBySalary(int minimumSalary);
}
