package com.techm.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.techm.dao.EmployeeDao;
import com.techm.model.Employee;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet("/EmployeeServlet")
public class EmployeeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String action = request.getParameter("action");
        EmployeeDao employeeDao = new EmployeeDao();

        if ("add".equalsIgnoreCase(action)) {
            // CREATE
            try {
                int id = Integer.parseInt(request.getParameter("employeeId"));
                String name = request.getParameter("employeeName");
                String designation = request.getParameter("designation");
                int experience = Integer.parseInt(request.getParameter("experience"));
                double salary = Double.parseDouble(request.getParameter("salary"));

                Employee emp = new Employee();
                emp.setEmployeeId(id);
                emp.setEmployeeName(name);
                
                emp.setDesignation(designation);
                emp.setExperience(experience);
                emp.setSalary(salary);

                boolean result = employeeDao.saveEmployee(emp);
                if (result) {
                    out.write("<h3>Employee Stored Successfully</h3>");
                } else {
                    out.write("<h3>Error in Storing Employee Data</h3>");
                }
            } catch (Exception e) {
                out.write("<h3>Error: Invalid Input</h3>");
            }
        } else if ("update".equalsIgnoreCase(action)) {
            // UPDATE
            try {
                int id = Integer.parseInt(request.getParameter("employeeId"));
                String name = request.getParameter("employeeName");
                String designation = request.getParameter("designation");
                int experience = Integer.parseInt(request.getParameter("experience"));
                double salary = Double.parseDouble(request.getParameter("salary"));

                Employee emp = new Employee();
                emp.setEmployeeId(id);
                emp.setEmployeeName(name);
                emp.setDesignation(designation);
                emp.setExperience(experience);
                emp.setSalary(salary);

                employeeDao.updateEmployee(emp);
                out.write("<h3>Employee Updated Successfully</h3>");
            } catch (Exception e) {
                out.write("<h3>Error: Update Failed</h3>");
            }
        } else if ("delete".equalsIgnoreCase(action)) {
            // DELETE
            String id = request.getParameter("employeeId");
            boolean result = employeeDao.deleteEmployee(id);
            if (result) {
                out.write("<h3>Employee Deleted Successfully</h3>");
            } else {
                out.write("<h3>Error in Deleting Employee</h3>");
            }
        }

        // SHOW ALL
        ArrayList<Employee> list = employeeDao.findAllEmployee();
        out.print("<h2>All Employees</h2>");
        out.print("<table border='1' width='100%'>");
        out.print("<tr><th>Id</th><th>Name</th><th>Designation</th><th>Experience</th><th>Salary</th></tr>");
        for (Employee emp : list) {
            out.print("<tr><td>" + emp.getEmployeeId() + "</td><td>" + emp.getEmployeeName() + "</td><td>" +
                    emp.getDesignation() + "</td><td>" + emp.getExperience() + "</td><td>" + emp.getSalary() + "</td></tr>");
        }
        out.print("</table>");

        out.print("<br><a href='EmployeeForm.html'>Back to Form</a>");
    }
}
