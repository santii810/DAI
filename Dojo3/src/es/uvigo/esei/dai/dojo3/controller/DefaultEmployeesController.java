package es.uvigo.esei.dai.dojo3.controller;

import java.util.List;

import es.uvigo.esei.dai.dojo3.EmployeeNotFoundException;
import es.uvigo.esei.dai.dojo3.SearchType;
import es.uvigo.esei.dai.dojo3.model.dao.EmployeesDAO;
import es.uvigo.esei.dai.dojo3.model.entity.Employee;

public class DefaultEmployeesController implements EmployeesController {
	private final EmployeesDAO dao;
	
	public DefaultEmployeesController(EmployeesDAO dao) {
		this.dao = dao;
	}

	@Override
	public Employee get(int id) throws EmployeeNotFoundException {
		return this.dao.get(id);
	}

	@Override
	public void create(Employee employee) {
		this.dao.create(employee);
	}
	
	@Override
	public void update(Employee employee) throws EmployeeNotFoundException {
		this.dao.update(employee);
	}

	@Override
	public void delete(int id) throws EmployeeNotFoundException {
		this.dao.delete(id);
	}

	@Override
	public List<Employee> list() {
		return this.dao.list();
	}

	@Override
	public List<Employee> list(SearchType type, String value)
	throws IllegalArgumentException {
		switch (type) {
		case NAME:
			return this.dao.listByName(value);
		case SURNAME:
			return this.dao.listBySurname(value);
		case SALARY:
			try {
				return this.dao.listBySalary(Integer.parseInt(value));
			} catch (NumberFormatException nfe) {
				throw new IllegalArgumentException(nfe);
			}
		default:
			throw new IllegalArgumentException("Unknown type: " + type);
		}
	}
}
