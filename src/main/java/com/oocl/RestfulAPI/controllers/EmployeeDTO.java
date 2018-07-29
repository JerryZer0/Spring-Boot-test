package com.oocl.RestfulAPI.controllers;
import com.oocl.RestfulAPI.entities.Employee;

public class EmployeeDTO {

    private final int id;
    private final String name;
    private final int age;
    private final String gender;
    //private final int companyId;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

//    public int getCompanyId() {
//        return companyId;
//    }

    public EmployeeDTO(Employee employee) {
        this.id = employee.getId();
        this.name = employee.getName();
        this.age = employee.getAge();
        this.gender = employee.getGender();
        //this.companyId = employee.getCompany().getId();
    }

    public EmployeeDTO(int id, String name, int age, String gender) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
    }
}
