package es.uvigo.esei.dai.dojo3.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;

import es.uvigo.esei.dai.dojo3.SearchType;
import es.uvigo.esei.dai.dojo3.controller.EmployeesController;
import es.uvigo.esei.dai.dojo3.model.entity.Employee;

public class EmployeesPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	
	private final JButton btnNewEmployee;
	
	private final JTextField txtSearch;
	private final JComboBox<SearchType> cmbSearchType;
	private final JButton btnSearch;
	private final JButton btnClear;
	
	private final JTable tableEmployees;
	
	private final EmployeesDataPanel dataPanel;
	
	private final EmployeesMediator mediator;

	
	public EmployeesPanel(EmployeesController controller) {
		super(new BorderLayout());
		
		this.mediator = new EmployeesMediator(controller);

		// TOP Panel
		this.txtSearch = new JTextField();
		this.cmbSearchType = new JComboBox<>(SearchType.values());
		this.cmbSearchType.setSelectedIndex(0);
		this.btnSearch = new JButton(new ActionSearch());
		this.btnClear = new JButton(new ActionClear());
		this.btnNewEmployee = new JButton(new ActionNewEmployee());
		this.mediator.setTxtSearch(this.txtSearch);
		
		
		final JPanel panelTop = new JPanel(new BorderLayout(10, 4));
		
		final JPanel panelSearchButtons = 
			new JPanel(new FlowLayout(FlowLayout.LEADING, 0, 0));
		panelSearchButtons.add(this.cmbSearchType);
		panelSearchButtons.add(this.btnSearch);
		panelSearchButtons.add(this.btnClear);
		
		final JPanel panelSearch = new JPanel(new BorderLayout());
		panelSearch.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));
		panelSearch.add(this.txtSearch, BorderLayout.CENTER);
		panelSearch.add(panelSearchButtons, BorderLayout.EAST);
		
		panelTop.add(this.btnNewEmployee, BorderLayout.EAST);
		panelTop.add(panelSearch, BorderLayout.CENTER);
		// End TOP Panel
		
		// Employees Table
		final EmployeeTableModel model = new EmployeeTableModel(controller.list());
		this.tableEmployees = new JTable(model);
		this.tableEmployees.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.tableEmployees.setRowSelectionAllowed(true);
		this.tableEmployees.setColumnSelectionAllowed(false);
		this.tableEmployees.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				final int index = EmployeesPanel.this.tableEmployees.getSelectedRow();
				
				if (index >= 0) {
					EmployeesPanel.this.mediator.editEmployee(model.getEmployee(index));
				}
			}
		});
		
		this.mediator.setTable(this.tableEmployees);
		// End Employees Table
		
		// Employees Data Panel
		this.dataPanel = new EmployeesDataPanel(this.mediator);
		this.mediator.setDataPanel(this.dataPanel);
		// End Employees Data Panel
		
		this.add(panelTop, BorderLayout.NORTH);
		this.add(new JScrollPane(this.tableEmployees), BorderLayout.CENTER);
		this.add(this.dataPanel, BorderLayout.EAST);
	}
	
	final class EmployeeTableModel extends AbstractTableModel {
		private static final long serialVersionUID = 1L;

		private List<Employee> employees;

		public EmployeeTableModel(List<Employee> employees) {
			this.employees = employees;
		}

		public void setEmployees(List<Employee> employees) {
			this.employees = employees;
			this.fireTableDataChanged();
		}
		
		public Employee getEmployee(int index) {
			return this.employees.get(index);
		}
		
		public int indexOf(Employee employee) {
			if (employee.getId() != null) {
				for (int i = 0; i < this.employees.size(); i++) {
					if (this.employees.get(i).getId().equals(employee.getId()))
						return i;
				}
			}
			
			return -1;
		}
		
		@Override
		public int getColumnCount() {
			return 3;
		}
		
		@Override
		public int getRowCount() {
			return this.employees.size();
		}
		
		@Override
		public String getColumnName(int column) {
			final String[] columns = new String[] {
				"Name", "Surname", "Salary"
			};
			return columns[column];
		}

		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			final Employee employee = this.employees.get(rowIndex);
			switch (columnIndex) {
			case 0:
				return employee.getName();
			case 1:
				return employee.getSurname();
			case 2:
				return employee.getSalary();
			default:
				return null;
			}
		}
	}
	
	private final class ActionSearch extends AbstractAction {
		private static final long serialVersionUID = 1L;

		private ActionSearch() {
			super("Search");
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				EmployeesPanel.this.mediator.search(
					(SearchType) EmployeesPanel.this.cmbSearchType.getSelectedItem(),
					EmployeesPanel.this.txtSearch.getText()
				);
			} catch (IllegalArgumentException iae) {
				JOptionPane.showMessageDialog(
					EmployeesPanel.this,
					"Valor de búsqueda incorrecto",
					"Error",
					JOptionPane.ERROR_MESSAGE
				);
			}
		}
	}
	
	public class ActionClear extends AbstractAction {
		private static final long serialVersionUID = 1L;
		
		public ActionClear() {
			super("Clear");
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				EmployeesPanel.this.mediator.clearSearch();
			} catch (IllegalArgumentException iae) {
				JOptionPane.showMessageDialog(
					EmployeesPanel.this,
					"Valor de búsqueda incorrecto",
					"Error",
					JOptionPane.ERROR_MESSAGE
				);
			}
		}
	}

	private final class ActionNewEmployee extends AbstractAction {
		private static final long serialVersionUID = 1L;

		private ActionNewEmployee() {
			super("New Employee");
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			EmployeesPanel.this.mediator.newEmployee();
		}
	}
}
