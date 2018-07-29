package com.oocl.RestfulAPI.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.oocl.RestfulAPI.entities.Employee;
import com.oocl.RestfulAPI.controllers.EmployeeController;
import com.oocl.RestfulAPI.services.EmployeeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(EmployeeController.class)
public class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployeeService service;

    @Autowired
    private ObjectMapper mapper;

    @Test
    public void should_hello_world() throws Exception {
        mockMvc.perform(get("/").accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"));
    }

    @Test
    public void should_return_employee_list() throws Exception {

        Employee employee1 = new Employee(1, "小明", 20, "male");
        Employee employee2 = new Employee(2, "小红", 18, "female");

        List<Employee> employeeList = Arrays.asList(employee1, employee2);
        given(service.getEmployeeList()).willReturn(employeeList);
        mockMvc.perform(get("/employees")).andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("小明"))
                .andExpect(jsonPath("$[0].id").value(1));
    }

    @Test
    public void should_return_employee_with_id_is_1() throws Exception {
        given(service.getEmployee(1)).willReturn(new Employee(1, "小明", 20, "male"));
        mockMvc.perform(get("/employees/1")).andExpect(status().isOk())
                .andExpect(jsonPath("name").value("小明"))
                .andExpect(jsonPath("id").value(1));
    }

    @Test
    public void should_return_employees_by_page() throws Exception {
        //given
        int pageNumber = 1;
        int pageSize = 2;
        List<Employee> employees = Arrays.asList(new Employee(1, "小明", 20, "male"));
        given(service.getEmployeeListInPage(pageNumber, pageSize)).willReturn(employees);
        //when
        ResultActions resultActions = mockMvc.perform(get("/employees/page/1/pageSize/2", pageNumber, pageSize));
        //then
        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1))).andDo(print())
                .andExpect(jsonPath("$[0].id").value(1))
               ;
    }

    @Test
    public void should_return_employees_by_gender() throws Exception {
        //given
        int pageNumber = 1;
        int pageSize = 2;
        List<Employee> employees = Arrays.asList(new Employee(1, "小明", 20, "male"),
                new Employee(2, "小光", 21, "female"));
        given(service.getEmployeeListByGender("male")).willReturn(employees);
        //when
        ResultActions resultActions = mockMvc.perform(get("/employees/male"));
        //then
        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2))).andDo(print())
                .andExpect(jsonPath("$[0].name").value("小明"))
                .andExpect(jsonPath("$[1].name").value("小光"))
        ;
    }

    @Test
    public void should_add_employee_successfully() throws Exception {
        //given
        Employee employee = new Employee(1, "小明", 20, "male");
        given(service.add(employee)).willReturn(true);
        //when
        ResultActions result = mockMvc.perform(post("/employees")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(employee)));
        //then
        result.andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void should_update_employee_successfully() throws Exception {
        //given
        Employee employee = new Employee(1, "小明", 20, "male");
        given(service.updateEmployee(anyInt(), any(Employee.class))).willReturn(true);
        //when
        ResultActions resultActions = mockMvc.perform(put("/employees/{0}", employee.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(employee)));
        //then
        resultActions.andExpect(status().isNoContent())
                .andDo(print());
    }

    @Test
    public void should_delete_by_id_successfully() throws Exception {
        //given
        Employee employee = new Employee(1, "小明", 20, "male");
        given(service.deleteEmployee(employee.getId())).willReturn(true);
        //when
        ResultActions resultActions = mockMvc.perform(delete("/employees/{0}", employee.getId()));
        //then
        resultActions.andExpect(status().isOk())
                .andDo(print());
    }
}
