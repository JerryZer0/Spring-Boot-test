package com.oocl.RestfulAPI.CompaniesAPI;

import com.oocl.RestfulAPI.EmployeesAPI.Employee;

import java.util.List;

public class Company {
    private int id;
    private String name;
    private List<Employee> employeeList;

    Company() {
    }

    public Company(int id, String name,List<Employee> employeeList) {
        this.id = id;
        this.name = name;
        this.employeeList = employeeList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    public void setEmployeeList(List<Employee> employeeList) {
        this.employeeList = employeeList;
    }
}