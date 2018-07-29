package com.oocl.RestfulAPI.controllers;

import com.oocl.RestfulAPI.entities.Company;

import java.util.List;
import java.util.stream.Collectors;

public class CompanyDTO {
    private final int id;
    private final String name;
    private final List<EmployeeDTO> employeeList;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<EmployeeDTO> getEmployeeList() {
        return employeeList;
    }

    public CompanyDTO(Company company){
        this.id = company.getId();
        this.name = company.getName();
        this.employeeList = company.getEmployeeList().stream().map(employee ->
                new EmployeeDTO(employee)).collect(Collectors.toList());
    }

    public CompanyDTO(int id, String name, List<EmployeeDTO> employeeList) {
        this.id = id;
        this.name = name;
        this.employeeList = employeeList;
    }
}