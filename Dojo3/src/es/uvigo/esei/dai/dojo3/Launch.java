package es.uvigo.esei.dai.dojo3;

import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import es.uvigo.esei.dai.dojo3.controller.DefaultEmployeesController;
import es.uvigo.esei.dai.dojo3.controller.EmployeesController;
import es.uvigo.esei.dai.dojo3.model.dao.EmployeesDAO;
import es.uvigo.esei.dai.dojo3.model.dao.EmployeesMapDAO;
import es.uvigo.esei.dai.dojo3.view.EmployeesPanel;

public class Launch {
	public static void main(String[] args) throws SQLException {
		// Una vez completado el ejercicio cambiar el DAO creado
		final EmployeesDAO dao = new EmployeesMapDAO();
//		final EmployeesDAO dao = createEmployeesJavaDBDAO(false);
//		final EmployeesDAO dao = createEmployeesMySQLDBDAO();
		
		final EmployeesController controller = new DefaultEmployeesController(dao);
		
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				changeLookAndFeelToNimbus();
				
				final JFrame frame = new JFrame("Employees Manager");
				frame.setContentPane(new EmployeesPanel(controller));
				
				frame.setSize(800, 600);
				frame.setLocationRelativeTo(null);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
			}
		});
	}
	
	/* Una vez completado el ejercicio descomentar este bloque de código.
	private final static EmployeesDAO createEmployeesJavaDBDAO(boolean createDatabase) throws SQLException {
		final JavaDBConnectionConfiguration configuration = new JavaDBConnectionConfiguration(
			"dojo3", "dojo3", null, "db/employees"
		);
		// La base de datos ya debería estar creada
		if (createDatabase)
			createDatabase(configuration);
		
		return new EmployeesDBDAO(ConnectionUtils.getConnection(configuration));
	}
	
	private final static void createJavaDBDatabase(JavaDBConnectionConfiguration configuration)
	throws SQLException {
		configuration.putAttribute("create", "true");
		final Connection connection = ConnectionUtils.getConnection(configuration);
		connection.createStatement().execute("CREATE SCHEMA DOJO3");
		connection.createStatement().execute("CREATE TABLE Employees "
			+ "(id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), "
			+ "name VARCHAR(100) NOT NULL, "
			+ "surname VARCHAR(100) NOT NULL, "
			+ "birth DATE NOT NULL, "
			+ "salary INTEGER NOT NULL, "
			+ "intern BOOLEAN NOT NULL,  PRIMARY KEY (id)"
		+ ")");
	}
	
	private final static EmployeesDAO createEmployeesMySQLDBDAO() throws SQLException {
		final Connection connection = ConnectionUtils.getConnection(
			new MySQLConnectionConfiguration(
					"dojo3", "dojo3", "localhost", "employees", 3306
			)
		);
		
		return new EmployeesDBDAO(connection);
	}*/

	private static void changeLookAndFeelToNimbus() {
		try {
			for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (Exception e) {
			// If Nimbus is not available, you can set the GUI to another
			// look and feel.
		}
	}
}
