package com.oocl.RestfulAPI.EmployeesAPI;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService {

    EmployeeList employees = new EmployeeList();

    Employee employee1 = new Employee(1, "小明", 20, "male");
    Employee employee2 = new Employee(2, "小红", 18, "female");
    Employee employee3 = new Employee(3, "小红", 18, "male");
    Employee employee4 = new Employee(4, "阿哥", 21, "male");

    public EmployeeService(List<Employee> employeeList) {
        List<Employee> employeeListReposery = employeeList;
        employees.setEmployees(employeeListReposery);
    }

    public EmployeeService() {
        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(employee1);
        employeeList.add(employee2);
        employeeList.add(employee3);
        employeeList.add(employee4);
        employees.setEmployees(employeeList);
    }

    public List<Employee> getEmployeeList() {
        List<Employee> employeeList = employees.getAllEmployees();
        return employeeList;
    }

    public Employee getEmployee(int indexId) {
        Employee e = employees.findById(indexId);
        return e;
    }

    public List<Employee> getEmployeeListByGender(String gender) {
        int size = employees.getContains();
        List<Employee> getByGender = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            if (employees.findById(i+1).getGender().equals(gender)) {
                getByGender.add(employees.findById(i+1));
            }
            System.out.println(size);
        }
        return getByGender;
    }

    public List<Employee> getEmployeeListInPage(int pageNumber, int pageSize) {
        int size = employees.getContains();
        int begin = (pageNumber - 1) * pageSize;
        List<Employee> getByPage = new ArrayList<>();
        for (int i = begin; i < size && ((i - begin) < pageSize); i++) {
            getByPage.add(employees.findById(i+1));
        }
        return getByPage;
    }

    public void add(Employee employee) {
        employees.add(employee);
    }

    public void updateEmployee(int id, Employee employee) {
        Employee employeeOri = employees.findById(id);
        if (employee.getAge() == 0) {
            employee.setAge(employeeOri.getAge());
        }
        if (employee.getName() == null) {
            employee.setName(employeeOri.getName());
        }
        if (employee.getGender() == null) {
            employee.setGender(employeeOri.getGender());
        }
        employee.setId(id);
        employees.update(id,employee);
    }

    public void deleteEmployee(int id) {
        employees.remove(id-1);
    }
}
