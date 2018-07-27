package com.oocl.RestfulAPI.EmployeesAPI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("employees")
    public List<Employee> getEmployeeList() {
        List<Employee> employeeList = employeeService.getEmployeeList();
        return employeeList;
    }

    @GetMapping("employees/{id}")
    public Employee getEmployeeById(@PathVariable int id) {
        Employee employee = employeeService.getEmployee(id);
        return employee;
    }

    @GetMapping("employees/page/{pageNumber}/pageSize/{pageSize}")
    public List<Employee> getEmployeeByPage(@PathVariable("pageNumber") int pageNumber, @PathVariable("pageSize") int pageSize) {
        List<Employee> employeeList = employeeService.getEmployeeListInPage(pageNumber,pageSize);
        return employeeList;
    }

    @GetMapping("employees/male")
    public List<Employee> getEmployeesByMale() {
        List<Employee> employeeList = employeeService.getEmployeeListByGender("male");
        return employeeList;
    }

    @GetMapping("employees/female")
    public List<Employee> getEmployeesByFemale() {
        List<Employee> employeeList = employeeService.getEmployeeListByGender("female");
        return employeeList;
    }

    @PostMapping("employees")
    public void addEmployee(@RequestBody Employee employee){
        employeeService.add(employee);
        System.out.println(employee.getName());
    }

    @PutMapping("employees/{id}")
    public void updateEmployee(@PathVariable int id,@RequestBody Employee employee){
        employeeService.updateEmployee(id,employee);
        System.out.println(employee.getAge());
        System.out.println(employee.getName());
    }

    @DeleteMapping("employees/{id}")
    public void deleteEmployee(@PathVariable int id){
        employeeService.deleteEmployee(id);
        System.out.println(id);
    }
}
