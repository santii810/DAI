package es.uvigo.esei.dai.dojo3.model.dao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import es.uvigo.esei.dai.dojo3.EmployeeNotFoundException;
import es.uvigo.esei.dai.dojo3.model.entity.Employee;

public class EmployeesMapDAO implements EmployeesDAO {
	private final Map<Integer, Employee> employees;
	
	private int idGenerator;
	
	public EmployeesMapDAO() {
		this.employees = new HashMap<>();
		
		final Calendar calendar = GregorianCalendar.getInstance();
		
		this.idGenerator = 1;
		calendar.set(1980, Calendar.FEBRUARY, 10);
		this.create(new Employee(
			"Manuel", "Martínez", calendar.getTime(), 10000, false
		));
		calendar.set(1975, Calendar.MARCH, 21);
		this.create(new Employee(
			"María", "Muñíz", calendar.getTime(), 12000, true
		));
		
	}

	@Override
	public Employee get(int id) throws EmployeeNotFoundException {
		if (this.employees.containsKey(id)) {
			return this.employees.get(id);
		} else {
			throw new EmployeeNotFoundException(id);
		}
	}

	@Override
	public void create(Employee employee) {
		employee.setId(this.employees.size());
		this.employees.put(this.idGenerator++, employee);
	}
	
	@Override
	public void update(Employee employee) throws EmployeeNotFoundException {
		if (this.employees.containsKey(employee.getId())) {
			this.employees.put(employee.getId(), employee);
		} else {
			throw new EmployeeNotFoundException(employee.getId());
		}
	}

	@Override
	public void delete(int id) throws EmployeeNotFoundException {
		if (this.employees.remove(id) == null) {
			throw new EmployeeNotFoundException(id);
		}
	}

	@Override
	public List<Employee> list() {
		return new ArrayList<>(this.employees.values());
	}

	@Override
	public List<Employee> listByName(String name) {
		final List<Employee> employees = this.list();
		
		final Iterator<Employee> itEmployees = employees.iterator();
		while (itEmployees.hasNext()) {
			if (!itEmployees.next().getName().contains(name)) {
				itEmployees.remove();
			}
		}
		
		return employees;
	}

	@Override
	public List<Employee> listBySurname(String surname) {
		final List<Employee> employees = this.list();
		
		final Iterator<Employee> itEmployees = employees.iterator();
		while (itEmployees.hasNext()) {
			if (!itEmployees.next().getSurname().contains(surname)) {
				itEmployees.remove();
			}
		}
		
		return employees;
	}

	@Override
	public List<Employee> listBySalary(int minimumSalary) {
		final List<Employee> employees = this.list();
		
		final Iterator<Employee> itEmployees = employees.iterator();
		while (itEmployees.hasNext()) {
			if (itEmployees.next().getSalary() < minimumSalary) {
				itEmployees.remove();
			}
		}
		
		return employees;
	}
}
