package com.oocl.RestfulAPI.services;

import com.oocl.RestfulAPI.EmployeesAPI.EmployeeList;
import com.oocl.RestfulAPI.controllers.EmployeeDTO;
import com.oocl.RestfulAPI.entities.Employee;
import com.oocl.RestfulAPI.entities.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService {

    private EmployeeRepository repository;

    @Autowired
    public EmployeeService(EmployeeRepository repository) {
        this.repository = repository;
    }

    //
//    EmployeeList employees = new EmployeeList();
//
//    Employee employee1 = new Employee(1, "小明", 20, "male");
//    Employee employee2 = new Employee(2, "小红", 18, "female");
//    Employee employee3 = new Employee(3, "小红", 18, "male");
//    Employee employee4 = new Employee(4, "阿哥", 21, "male");

//    public EmployeeService(List<Employee> employeeList) {
//        List<Employee> employeeListReposery = employeeList;
//        employees.setEmployees(employeeListReposery);
//    }

//    public EmployeeService() {
//        List<Employee> employeeList = new ArrayList<>();
//        employeeList.add(employee1);
//        employeeList.add(employee2);
//        employeeList.add(employee3);
//        employeeList.add(employee4);
//        employees.setEmployees(employeeList);
//    }

    public List<Employee> getEmployeeList() {
        List<Employee> employeeList = repository.findAll();
        return employeeList;
    }

    public Employee getEmployee(int indexId) {
        Employee employee = repository.findById(indexId).get();
        return employee;
    }

    public List<Employee> getEmployeeListByGender(String gender) {
        List<Employee> employees = (List<Employee>) repository.findByGender(gender);
        return employees;
    }

    public List<Employee> getEmployeeListInPage(int pageNumber, int pageSize) {
        List<Employee> employeeLiat = (List<Employee>) repository.findAll(new PageRequest(0, 2));
//        for (int i = begin; i < size && ((i - begin) < pageSize); i++) {
//            getByPage.add(companies.get(i));
//        }
        return employeeLiat;
    }

    public void add(Employee employee) {
        repository.save(employee);
    }

    public void updateEmployee(int id, Employee employee) {
        employee.setId(id);
        repository.save(employee);
    }

    public void deleteEmployee(int id) {
        Employee employee = repository.findById(id).get();
        repository.delete(employee);
    }
}
