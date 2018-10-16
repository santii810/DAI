package es.uvigo.esei.dai.dojo3;

public class EmployeeNotFoundException extends Exception {
	private static final long serialVersionUID = 1L;
	private final int id;

	public EmployeeNotFoundException(int id) {
		this.id = id;
	}

	public EmployeeNotFoundException(String message, int id) {
		super(message);
		this.id = id;
	}

	public EmployeeNotFoundException(Throwable cause, int id) {
		super(cause);
		this.id = id;
	}

	public EmployeeNotFoundException(String message, Throwable cause, int id) {
		super(message, cause);
		this.id = id;
	}

	public EmployeeNotFoundException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace, int id) {
		super(message, cause, enableSuppression, writableStackTrace);
		this.id = id;
	}
	
	public int getId() {
		return this.id;
	}
}
