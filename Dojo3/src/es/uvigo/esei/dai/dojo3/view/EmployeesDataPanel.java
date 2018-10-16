package es.uvigo.esei.dai.dojo3.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import es.uvigo.esei.dai.dojo3.EmployeeNotFoundException;
import es.uvigo.esei.dai.dojo3.model.entity.Employee;

public class EmployeesDataPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	
	private final EmployeesMediator mediator;
	
	private final JTextField txtName;
	private final JTextField txtSurname;
	private final JFormattedTextField ftxtSalary;
	private final JFormattedTextField ftxtBirth;
	private final JCheckBox chkIntern;

	private final JButton btnSaveOrUpdate;
	private final JButton btnDelete;
	private final JButton btnReset;
	
	private Employee employee;

	public EmployeesDataPanel(EmployeesMediator mediator) {
		super(new FlowLayout(FlowLayout.LEADING));
		
		this.mediator = mediator;
		
		// Header
		final JLabel lblHeader = new JLabel("Employee Data");
		lblHeader.setFont(lblHeader.getFont().deriveFont(Font.BOLD));
		lblHeader.setHorizontalTextPosition(JLabel.CENTER);
		// End Header
		
		// Form
		this.txtName = new JTextField();
		this.txtName.setPreferredSize(new Dimension(200, (int) this.txtName.getPreferredSize().getHeight()));
		this.txtSurname = new JTextField();
		this.ftxtSalary = new JFormattedTextField(NumberFormat.getIntegerInstance());
		this.ftxtBirth = new JFormattedTextField(new SimpleDateFormat("dd/MM/yyyy"));
		this.chkIntern = new JCheckBox();
		
		final String[] labels = new String[] {
			"Name", "Surname", "Salary", "Birth", "Is intern?"
		};
		final JComponent[] components = new JComponent[] {
			this.txtName, this.txtSurname, 
			this.ftxtSalary, this.ftxtBirth, 
			this.chkIntern
		};
		
		final JPanel panelData = new JPanel(new SpringLayout());
		final ValidateFocusAdapter validator = new ValidateFocusAdapter();
		for (int i = 0; i < labels.length; i++) {
			final JLabel label = new JLabel(labels[i]);
			label.setLabelFor(components[i]);
			
			panelData.add(label);
			panelData.add(components[i]);
			
			components[i].addFocusListener(validator);
		}
		SpringUtilities.makeCompactGrid(panelData, 5, 2, 6, 6, 6, 6);
		// End Form
		
		// Buttons
		final JPanel panelButtons = new JPanel();
		this.btnSaveOrUpdate = new JButton(new ActionSaveOrUpdate());
		this.btnReset = new JButton(new ActionReset());
		this.btnDelete = new JButton(new ActionDelete());
		
		panelButtons.add(this.btnSaveOrUpdate);
		panelButtons.add(this.btnReset);
		panelButtons.add(this.btnDelete);
		// End Buttons
		
		final JPanel containerPanel = new JPanel(new BorderLayout());
		containerPanel.add(lblHeader, BorderLayout.NORTH);
		containerPanel.add(panelData, BorderLayout.CENTER);
		containerPanel.add(panelButtons, BorderLayout.SOUTH);
		
		this.add(containerPanel);
		
		this.newEmployee();
	}
	
	protected void validateFields() {
		this.btnSaveOrUpdate.setEnabled(
			!(this.txtName.getText().trim().isEmpty() ||
			this.txtSurname.getText().trim().isEmpty() ||
			this.ftxtSalary.getText().trim().isEmpty() ||
			this.ftxtBirth.getText().trim().isEmpty())
		);
	}

	public Employee getEmployee() {
		this.employee.setName(this.txtName.getText());
		this.employee.setSurname(this.txtSurname.getText());
		this.employee.setSalary(((Number) this.ftxtSalary.getValue()).intValue());
		this.employee.setBirth((Date) this.ftxtBirth.getValue());
		this.employee.setIntern(this.chkIntern.isSelected());
		
		return this.employee;
	}
	
	public void setEmployee(Employee employee) {
		this.employee = employee;
		
		this.txtName.setText(this.employee.getName());
		this.txtSurname.setText(this.employee.getSurname());
		this.ftxtSalary.setValue(this.employee.getSalary());
		this.ftxtBirth.setValue(this.employee.getBirth());
		this.chkIntern.setSelected(this.employee.isIntern());
		
		if (this.isNewEmployee()) {
			this.btnSaveOrUpdate.setText("Save");
			this.btnDelete.setEnabled(false);
		} else {
			this.btnSaveOrUpdate.setText("Update");
			this.btnDelete.setEnabled(true);
		}
		this.validateFields();
	}
	
	public void newEmployee() {
		this.setEmployee(new Employee());
	}

	private boolean isNewEmployee() {
		return this.employee.getId() == null;
	}
	
	private final class ValidateFocusAdapter extends FocusAdapter {
		@Override
		public void focusLost(FocusEvent e) {
			EmployeesDataPanel.this.validateFields();
		}
	}
	
	private final class ActionDelete extends AbstractAction {
		private static final long serialVersionUID = 1L;

		private ActionDelete() {
			super("Delete");
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				EmployeesDataPanel.this.mediator.deleteEmployee(
					EmployeesDataPanel.this.employee
				);
			} catch (EmployeeNotFoundException e1) {
				JOptionPane.showMessageDialog(
					EmployeesDataPanel.this,
					"Error al eliminar el empleado. Identificador desconocido",
					"Error",
					JOptionPane.ERROR_MESSAGE
				);
			}
		}
	}

	private final class ActionReset extends AbstractAction {
		private static final long serialVersionUID = 1L;

		private ActionReset() {
			super("Reset");
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			EmployeesDataPanel.this.setEmployee(EmployeesDataPanel.this.employee);
		}
	}

	private final class ActionSaveOrUpdate extends AbstractAction {
		private static final long serialVersionUID = 1L;

		private ActionSaveOrUpdate() {
			super("Save");
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			if (EmployeesDataPanel.this.isNewEmployee()) {
				EmployeesDataPanel.this.mediator.createEmployee(
					EmployeesDataPanel.this.getEmployee()
				);
			} else {
				try {
					EmployeesDataPanel.this.mediator.updateEmployee(
						EmployeesDataPanel.this.getEmployee()
					);
				} catch (EmployeeNotFoundException e1) {
					JOptionPane.showMessageDialog(
						EmployeesDataPanel.this,
						"Error al actualizar los datos del empleado. Identificador desconocido",
						"Error",
						JOptionPane.ERROR_MESSAGE
					);
				}
			}
		}
	}
}
