package es.uvigo.esei.dai.dojo3;

public enum SearchType {
	NAME("Name"), SURNAME("Surname"), SALARY("Salary");
	
	private final String label;
	
	private SearchType(String label) {
		this.label = label;
	}
	
	@Override
	public String toString() {
		return this.label;
	}
}
