package com.oocl.RestfulAPI.controllers;

import com.oocl.RestfulAPI.services.EmployeeService;
import com.oocl.RestfulAPI.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @RequestMapping("/")
    String getHelloWorld() {
        return "Hello World!";
    }

    @GetMapping(path = "employees", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Employee> getEmployeeList() {
        List<Employee> employeeList = employeeService.getEmployeeList();
        return employeeList;
    }

    @GetMapping(path = "employees/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Employee getEmployeeById(@PathVariable int id) {
        Employee employee = employeeService.getEmployee(id);
        return employee;
    }

    @GetMapping(path = "employees/page/{pageNumber}/pageSize/{pageSize}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Employee> getEmployeeByPage(@PathVariable("pageNumber") int pageNumber, @PathVariable("pageSize") int pageSize) {
        List<Employee> employeeList = employeeService.getEmployeeListInPage(pageNumber,pageSize);
        return employeeList;
    }

    @GetMapping(path = "employees/male", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Employee> getEmployeesByMale() {
        List<Employee> employeeList = employeeService.getEmployeeListByGender("male");
        return employeeList;
    }

    @GetMapping(path = "employees/female", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Employee> getEmployeesByFemale() {
        List<Employee> employeeList = employeeService.getEmployeeListByGender("female");
        return employeeList;
    }

    @PostMapping(path = "employees", produces = MediaType.APPLICATION_JSON_VALUE)
    public void addEmployee(@RequestBody Employee employee){
        employeeService.add(employee);
        System.out.println(employee.getName());
    }

    @PutMapping(path = "employees/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public void updateEmployee(@PathVariable int id,@RequestBody Employee employee){
        employeeService.updateEmployee(id,employee);
        System.out.println(employee.getAge());
        System.out.println(employee.getName());
    }

    @DeleteMapping(path = "employees/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public void deleteEmployee(@PathVariable int id){
        employeeService.deleteEmployee(id);
        System.out.println(id);
    }
}
