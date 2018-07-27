package com.oocl.RestfulAPI.EmployeesAPI;

import java.util.ArrayList;
import java.util.List;

public class EmployeeList {
    private List<Employee> employees = new ArrayList<>();

    EmployeeList(){

    }

    public void add(Employee employee){
        employees.add(employee);
    }

    public List<Employee> getAllEmployees(){
        return employees;
    }
    public void setEmployees(List<Employee> employees){
        this.employees = employees;
    }

    public Employee findById(int id){
        int indexId = id-1;
        Employee employee = employees.get(indexId);
        return employee;
    }

    public void update(int id,Employee employee){
        employees.remove(id-1);
        employee.setId(id);
        employees.add(employee);
    }

    public void remove(int id){
        employees.remove(id-1);
    }

    public void remove(Employee employee){
        employees.remove(employee);
    }

    public int getContains(){
        return employees.size();}
}
