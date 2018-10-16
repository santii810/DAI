package es.uvigo.esei.dai.dojo3.model.entity;

import java.util.Date;

public class Employee {
	private Integer id;
	private String name;
	private String surname;
	private int salary;
	private Date birth;
	private boolean intern;

	public Employee() {
	}
	
	public Employee(String name, String surname, Date birth, int salary, boolean intern) {
		this.name = name;
		this.surname = surname;
		this.salary = salary;
		this.birth = birth;
		this.intern = intern;
	}
	
	public Employee(Integer id, String name, String surname, Date birth, int salary, boolean intern) {
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.birth = birth;
		this.salary = salary;
		this.intern = intern;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	public Date getBirth() {
		return birth;
	}

	public void setBirth(Date birth) {
		this.birth = birth;
	}

	public boolean isIntern() {
		return intern;
	}

	public void setIntern(boolean intern) {
		this.intern = intern;
	}
}
