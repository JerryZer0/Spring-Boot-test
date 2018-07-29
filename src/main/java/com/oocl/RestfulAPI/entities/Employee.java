package com.oocl.RestfulAPI.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private int age;
    private String gender;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

    public Employee() {
    }

    public Employee(String name, int age, String gender, Company company) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.company = company;
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
}
