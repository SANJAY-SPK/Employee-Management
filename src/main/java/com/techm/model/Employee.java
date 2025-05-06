package com.techm.model;

public class Employee {
	
	public int getEmployeeId() 
	{ 
		return employeeId; 
	}
	public void setEmployeeId(int employeeId)
	{ 
		this.employeeId = employeeId; 
	}
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public int getExperience() {
		return experience;
	}
	public void setExperience(int experience) {
		this.experience = experience;
	}
	public double getSalary() {
		return salary;
	}
	public void setSalary(double salary) {
		this.salary = salary;
	}
	private int employeeId;
	private String employeeName;
	private String designation;
	private int experience;
	private double salary;

}
