package com.oocl.RestfulAPI.controllers;

import com.oocl.RestfulAPI.entities.Employee;
import com.oocl.RestfulAPI.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Object> updateEmployee(@PathVariable("id") int id, @RequestBody Employee employee){
        employeeService.updateEmployee(id,employee);
        System.out.println(employee.getAge());
        System.out.println(employee.getName());

        if ( employeeService.updateEmployee(id,employee)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @DeleteMapping(path = "employees/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> deleteEmployee(@PathVariable int id){
        if (employeeService.deleteEmployee(id)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
