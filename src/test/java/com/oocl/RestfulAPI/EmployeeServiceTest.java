package com.oocl.RestfulAPI;

import com.oocl.RestfulAPI.EmployeesAPI.Employee;
import com.oocl.RestfulAPI.EmployeesAPI.EmployeeService;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;


public class EmployeeServiceTest {

    @Test
    public void should_return_the_hole_employee_list() {
        List<Employee> employeeList = new ArrayList<>();
        Employee employee1 = new Employee(1, "小明", 20, "male");
        Employee employee2 = new Employee(2, "小红", 18, "female");
        employeeList.add(employee1);
        employeeList.add(employee2);
        EmployeeService employeeService = new EmployeeService(employeeList);

        assertThat(employeeService.getEmployeeList(), is(employeeList));
    }

    @Test
    public void should_return_the_employee_with_id_is_1() {
        List<Employee> employeeList = new ArrayList<>();
        Employee employee1 = new Employee(1, "小明", 20, "male");
        Employee employee2 = new Employee(2, "小红", 18, "female");
        employeeList.add(employee1);
        employeeList.add(employee2);
        EmployeeService employeeService = new EmployeeService(employeeList);

        assertThat(employeeService.getEmployee(1), is(employee1));
    }

    @Test
    public void should_return_the_employee_with_geder_is_male_list() {
        List<Employee> employeeList = new ArrayList<>();
        Employee employee1 = new Employee(1, "小明", 20, "male");
        Employee employee2 = new Employee(2, "小红", 18, "female");
        Employee employee3 = new Employee(3, "小红", 18, "male");
        employeeList.add(employee1);
        employeeList.add(employee2);
        employeeList.add(employee3);

        EmployeeService employeeService = new EmployeeService(employeeList);
        employeeList.remove(employee2);

        assertThat(employeeService.getEmployeeListByGender("male"), is(employeeList));
    }

    @Test
    public void should_return_the_first_2_employees_with_1_page_and_size_is_2() {
        List<Employee> employeeList = new ArrayList<>();
        Employee employee1 = new Employee(1, "小明", 20, "male");
        Employee employee2 = new Employee(2, "小红", 18, "female");
        Employee employee3 = new Employee(3, "小红", 18, "male");
        employeeList.add(employee1);
        employeeList.add(employee2);
        employeeList.add(employee3);

        EmployeeService employeeService = new EmployeeService(employeeList);
        employeeList.remove(employee3);

        assertThat(employeeService.getEmployeeListInPage(1, 2), is(employeeList));
    }

    @Test
    public void should_return_the_3_employees_with_add_3() {
        List<Employee> employeeList = new ArrayList<>();
        Employee employee1 = new Employee(1, "小明", 20, "male");
        Employee employee2 = new Employee(2, "小红", 18, "female");
        Employee employee3 = new Employee(3, "小红", 18, "male");
        employeeList.add(employee1);
        employeeList.add(employee2);

        EmployeeService employeeService = new EmployeeService(employeeList);
        employeeService.add(employee3);
        employeeList.add(employee3);
        assertThat(employeeService.getEmployeeList(), is(employeeList));
    }

    @Test
    public void should_return_the_new_employees_info_with_update_1() {
        List<Employee> employeeList = new ArrayList<>();
        Employee employee1 = new Employee(1, "小明", 20, "male");
        Employee employee2 = new Employee(2, "小红", 18, "female");
        Employee employee3 = new Employee(0, "小蓝", 18, "male");
        employeeList.add(employee1);
        employeeList.add(employee2);

        EmployeeService employeeService = new EmployeeService(employeeList);
        employeeService.updateEmployee(2, employee3);
        employee3.setId(2);
        assertThat(employeeService.getEmployee(2), is(employee3));
    }

    @Test
    public void should_delete_the_employee_info_with_delete_3() {
        List<Employee> employeeList = new ArrayList<>();
        Employee employee1 = new Employee(1, "小明", 20, "male");
        Employee employee2 = new Employee(2, "小红", 18, "female");
        Employee employee3 = new Employee(3, "小蓝", 18, "male");
        employeeList.add(employee1);
        employeeList.add(employee2);
        employeeList.add(employee3);

        EmployeeService employeeService = new EmployeeService(employeeList);
        employeeService.deleteEmployee(3);
        employeeList.remove(employee3);
        assertThat(employeeService.getEmployeeList(), is(employeeList));
    }
}
