package com.oocl.RestfulAPI.service;

import com.oocl.RestfulAPI.entities.Company;
import com.oocl.RestfulAPI.entities.Employee;
import com.oocl.RestfulAPI.entities.EmployeeRepository;
import com.oocl.RestfulAPI.services.EmployeeService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {

    @Mock
    EmployeeRepository repository;

    @Test
    public void should_return_the_hole_employee_list() {
        //given
        EmployeeService employeeService = new EmployeeService(repository);
        List<Employee> employeeList = new ArrayList<>(
                Arrays.asList(new Employee("小红", 18, "female", new Company("hahha"))));

        //when
        when(repository.findAll()).thenReturn(employeeList);
        //then
        assertThat(employeeService.getEmployeeList(), is(employeeList));
    }

    @Test
    public void should_return_the_employee_with_id_is_1() {
        //given
        Employee employee1 = new Employee(1, "小明", 20, "male");
        EmployeeService employeeService = new EmployeeService(repository);

        //when
        when(repository.findById(1)).thenReturn(java.util.Optional.ofNullable(employee1));
        //then
        assertThat(employeeService.getEmployee(1), is(employee1));
    }

    @Test
    public void should_return_the_employee_with_geder_is_male_list() {
        //given
        EmployeeService employeeService = new EmployeeService(repository);
        List<Employee> employeeList = new ArrayList<>();
        Employee employee1 = new Employee(1, "小明", 20, "male");
//        Employee employee2 = new Employee(2, "小红", 18, "female");
        Employee employee3 = new Employee(3, "小红", 18, "male");
        employeeList.add(employee1);
        employeeList.add(employee3);
        //when
        when(repository.findByGender("male")).thenReturn(employeeList);

        //then
        assertThat(employeeService.getEmployeeListByGender("male"), is(employeeList));
    }

//    @Test
//    public void should_return_the_first_2_employees_with_1_page_and_size_is_2() {
//        //given
//        EmployeeService employeeService = new EmployeeService(repository);
//        List<Employee> employeeList = new ArrayList<>();
//        Employee employee1 = new Employee(1, "小明", 20, "male");
//        Employee employee2 = new Employee(2, "小红", 18, "female");
//        Employee employee3 = new Employee(3, "小红", 18, "male");
//        employeeList.add(employee1);
//        employeeList.add(employee2);
//        //when
//        when(repository.findAll(new PageRequest(1,2))).thenReturn((Page<Employee>) employeeList);
//
//        assertThat(employeeService.getEmployeeListInPage(1, 2), is(employeeList));
//    }

    @Test
    public void should_add_employees_successfully() {
        //given
        EmployeeService employeeService = new EmployeeService(repository);
        List<Employee> employeeList = new ArrayList<>();
        Employee employee1 = new Employee(1, "小明", 20, "male");
        employeeList.add(employee1);
        //when
        employeeService.add(employee1);
        //then
        verify(repository).save(employee1);
    }

    @Test
    public void should_update_employee_info_successfully() {
        //given
        EmployeeService employeeService = new EmployeeService(repository);
        List<Employee> employeeList = new ArrayList<>();
        Employee employee1 = new Employee(1, "小明", 20, "male");
        //employeeList.add(employee1);

        //when
        employeeService.updateEmployee(1,employee1);
        //then
        verify(repository).save(employee1);
    }

    @Test
    public void should_delete_the_employee_info_with_delete_successfully() {
        //given
        EmployeeService employeeService = new EmployeeService(repository);
        Employee employee1 = new Employee(1, "小明", 20, "male");
        //employeeList.add(employee1);

        //when
        when(repository.findById(1)).thenReturn(java.util.Optional.ofNullable(employee1));
        employeeService.deleteEmployee(1);

        //then
        verify(repository).delete(employee1);
    }
}
