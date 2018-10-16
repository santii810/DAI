package es.uvigo.esei.dai.dojo3.view;

import java.util.List;

import javax.swing.JTable;
import javax.swing.JTextField;

import es.uvigo.esei.dai.dojo3.EmployeeNotFoundException;
import es.uvigo.esei.dai.dojo3.SearchType;
import es.uvigo.esei.dai.dojo3.controller.EmployeesController;
import es.uvigo.esei.dai.dojo3.model.entity.Employee;
import es.uvigo.esei.dai.dojo3.view.EmployeesPanel.EmployeeTableModel;

class EmployeesMediator {
	private final EmployeesController controller;

	private EmployeesDataPanel dataPanel;
	private JTable table;
	private JTextField txtSearch;

	public EmployeesMediator(EmployeesController controller) {
		this.controller = controller;
	}

	public void setDataPanel(EmployeesDataPanel dataPanel) {
		this.dataPanel = dataPanel;
	}

	public void setTable(JTable table) {
		this.table = table;
	}
	
	public void setTxtSearch(JTextField txtSearch) {
		this.txtSearch = txtSearch;
	}

	public void newEmployee() {
		this.dataPanel.newEmployee();
		this.table.clearSelection();
	}

	public void createEmployee(Employee employee) {
		this.controller.create(employee);

		this.clearSearch();
		this.dataPanel.setEmployee(employee);
		this.selectEmployee(employee);
	}

	public void editEmployee(Employee employee) {
		this.dataPanel.setEmployee(employee);
	}

	public void updateEmployee(Employee employee) 
	throws EmployeeNotFoundException {
		this.controller.update(employee);
		
		this.clearSearch();
		this.dataPanel.setEmployee(employee);
		this.selectEmployee(employee);
	}

	public void deleteEmployee(Employee employee)
	throws EmployeeNotFoundException {
		this.controller.delete(employee.getId());
		
		this.clearSearch();
		this.table.clearSelection();
		this.dataPanel.newEmployee();
	}

	public void clearSearch() {
		this.search(null, null);
		this.txtSearch.setText("");
	}
	
	public void search(SearchType type, String value) {
		final List<Employee> employees;

		if (value == null || value.trim().isEmpty()) {
			employees = this.controller.list();
		} else {
			employees = this.controller.list(type, value.trim());
		}

		this.updateEmployeeList(employees);
		this.dataPanel.newEmployee();
	}

	private void updateEmployeeList(final List<Employee> employees) {
		((EmployeeTableModel) this.table.getModel()).setEmployees(employees);
	}

	private void selectEmployee(final Employee employee) {
		final EmployeeTableModel model = (EmployeeTableModel) this.table.getModel();
		final int index = model.indexOf(employee);

		if (index >= 0) {
			this.table.setRowSelectionInterval(index, index);
		}
	}
}
