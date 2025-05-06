package com.techm.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.techm.model.Employee;
import com.techm.util.DBUtil;

public class EmployeeDao {

	public boolean saveEmployee(Employee employee) {
	    Connection connection = DBUtil.createConnection();
	    String query = "INSERT INTO employeedb (employeeId, employeeName, designation, experience, salary) VALUES (?, ?, ?, ?, ?)";
	    try {
	        PreparedStatement statement = connection.prepareStatement(query);
	        statement.setInt(1, employee.getEmployeeId());
	        statement.setString(2, employee.getEmployeeName());
	        statement.setString(3, employee.getDesignation());
	        statement.setInt(4, employee.getExperience());
	        statement.setDouble(5, employee.getSalary());

	        int result = statement.executeUpdate();
	        return result == 1;
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return false;
	}


    public boolean deleteEmployee(String employeeId) {
        Connection connection = DBUtil.createConnection();
        String query = "DELETE FROM employeedb WHERE employeeId = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, employeeId);
            int result = statement.executeUpdate();
            return result == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Employee findEmployee(String employeeId) {
        Connection connection = DBUtil.createConnection();
        String query = "SELECT * FROM employeedb WHERE employeeId = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, employeeId);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                Employee employee = new Employee();
                employee.setEmployeeId(rs.getInt("employeeId"));
                employee.setEmployeeName(rs.getString("employeeName"));
                employee.setDesignation(rs.getString("designation"));
                employee.setExperience(rs.getInt("experience"));
                employee.setSalary(rs.getDouble("salary"));
                return employee;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<Employee> findAllEmployee() {
        ArrayList<Employee> employeeList = new ArrayList<>();
        Connection connection = DBUtil.createConnection();
        String query = "SELECT * FROM employeedb";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Employee employee = new Employee();
                employee.setEmployeeId(rs.getInt("employeeId"));
                employee.setEmployeeName(rs.getString("employeeName"));
                employee.setDesignation(rs.getString("designation"));
                employee.setExperience(rs.getInt("experience"));
                employee.setSalary(rs.getDouble("salary"));
                employeeList.add(employee);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employeeList;
    }

    public Employee updateEmployee(Employee employee) {
        Connection connection = DBUtil.createConnection();
        String query = "UPDATE employeedb SET employeeName = ?, designation = ?, experience = ?, salary = ? WHERE employeeId = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, employee.getEmployeeName());
            statement.setString(2, employee.getDesignation());
            statement.setInt(3, employee.getExperience());
            statement.setDouble(4, employee.getSalary());
            statement.setInt(5, employee.getEmployeeId());

            int result = statement.executeUpdate();
            if (result == 1) {
                return employee;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
