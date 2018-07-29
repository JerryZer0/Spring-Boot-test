package com.oocl.RestfulAPI.controller;

import com.oocl.RestfulAPI.entities.Employee;
import com.oocl.RestfulAPI.controllers.EmployeeController;
import com.oocl.RestfulAPI.services.EmployeeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(EmployeeController.class)
public class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployeeService service;

//    @Test
//    public void should_hello_world() throws Exception{
//        mockMvc.perform(get("/").accept(MediaType.parseMediaType("application/json")))
//                .andExpect(status().isOk())
//                .andExpect(content().contentType("application/json"));
//    }

//    @Test
//    public void should_return_employee_list() throws Exception {
//
//        Employee employee1 = new Employee(1, "小明", 20, "male");
//        Employee employee2 = new Employee(2, "小红", 18, "female");
//
//        List<Employee> employeeList = new ArrayList<>();
//        employeeList.add(employee1);
//        employeeList.add(employee2);
//        given(service.getEmployeeList()).willReturn(employeeList);
//        mockMvc.perform(get("/employees")).andExpect(status().isOk())
//                .andExpect(jsonPath("$[0].name").value("小明"))
//                .andExpect(jsonPath("$[0].id").value(1))
//                .andExpect(jsonPath("$[1].age").value(18))
//                .andExpect(jsonPath("$[1].gender").value("female"));
//    }
//
//    @Test
//    public void should_return_employee_with_id_is_1() throws Exception {
//        List<Employee> employeeList = new ArrayList<>();
//        given(service.getEmployee(1)).willReturn(new Employee(1, "小明", 20, "male"));
//        mockMvc.perform(get("/employees/1")).andExpect(status().isOk())
//                .andExpect(jsonPath("name").value("小明"))
//                .andExpect(jsonPath("id").value(1));
//    }
//
//    @Test
//    public void should_add_employee_successfully(){
//
//    }
}
