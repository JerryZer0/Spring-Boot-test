package com.oocl.RestfulAPI.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.oocl.RestfulAPI.controllers.CompanyController;
import com.oocl.RestfulAPI.controllers.EmployeeController;
import com.oocl.RestfulAPI.entities.Company;
import com.oocl.RestfulAPI.entities.Employee;
import com.oocl.RestfulAPI.services.CompanyService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(CompanyController.class)
public class CompanyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CompanyService service;

    @Autowired
    private ObjectMapper mapper;

    @Test
    public void should_return_company_list() throws Exception {

        //given
        List<Employee> employeelist = Arrays.asList(new Employee(1,"joker",10,"female"));
        Company company1 = new Company(1,"wahah",employeelist);
        Company company2 = new Company(2,"xiaoxixi",employeelist);

        List<Company> companyList = Arrays.asList(company1, company2);
        given(service.getCompanyList()).willReturn(companyList);
        //when
        ResultActions resultActions = mockMvc.perform(get("/companies"));
        //then
        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("wahah"))
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    public void should_return_company_with_id_is_1() throws Exception {
        //given
        List<Employee> employeelist = Arrays.asList(new Employee(1,"joker",10,"female"));
        Company company = new Company(1,"wahah",employeelist);
        given(service.getCompany(1)).willReturn(company);
        //when
        ResultActions resultActions = mockMvc.perform(get("/companies/1",company.getId()));
        //then
        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("name").value("wahah"))
                .andExpect(jsonPath("id").value(1));
    }

    @Test
    public void should_return_employees_by_page() throws Exception {
        //given
        int pageNumber = 1;
        int pageSize = 2;
        List<Employee> employeelist = Arrays.asList(new Employee(1,"joker",10,"female"));
        Company company1 = new Company(1,"wahah",employeelist);
        Company company2 = new Company(2,"xiaoxixi",employeelist);
        List<Company> companyList = Arrays.asList(company1, company2);
        given(service.getCompaniesInPage(pageNumber, pageSize)).willReturn(companyList);
        //when
        ResultActions resultActions = mockMvc.perform(get("/companies/page/1/pageSize/2", pageNumber, pageSize));
        //then
        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2))).andDo(print())
                .andExpect(jsonPath("$[0].id").value(1))
        ;
    }

    @Test
    public void should_return_employees_by_company_id() throws Exception {
        //given
        List<Employee> employeelist = Arrays.asList(new Employee(1,"joker",10,"female")
                ,new Employee(2,"her",17,"female"));
        Company company = new Company(1,"wahah",employeelist);

        given(service.getEmployeesByCompanyId(1)).willReturn(employeelist);
        //when
        ResultActions resultActions = mockMvc.perform(get("/companies/{0}/employees",company.getId()));
        //then
        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2))).andDo(print())
                .andExpect(jsonPath("$[0].name").value("joker"))
                .andExpect(jsonPath("$[1].name").value("her"))
        ;
    }

    @Test
    public void should_add_company_successfully() throws Exception {
        //given
        List<Employee> employeelist = Arrays.asList(new Employee(1,"joker",10,"female")
                ,new Employee(2,"her",17,"female"));
        Company company = new Company(1,"wahah",employeelist);
        given(service.add(company)).willReturn(true);
        //when
        ResultActions result = mockMvc.perform(post("/companies")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(company)));
        //then
        result.andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void should_update_employee_successfully() throws Exception {
        //given
        List<Employee> employeelist = Arrays.asList(new Employee(1,"joker",10,"female"));
        Company company = new Company(1,"wahah",employeelist);
        given(service.updateCompany(anyInt(), any(Company.class))).willReturn(true);
        //when
        ResultActions resultActions = mockMvc.perform(put("/companies/{0}", company.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(company)));
        //then
        resultActions.andExpect(status().isNoContent())
                .andDo(print());
    }

    @Test
    public void should_delete_by_id_successfully() throws Exception {
        //given
        List<Employee> employeelist = Arrays.asList(new Employee(1,"joker",10,"female"));
        Company company = new Company(1,"wahah",employeelist);
        given(service.deleteCompany(company.getId())).willReturn(true);
        //when
        ResultActions resultActions = mockMvc.perform(delete("/companies/{0}", company.getId()));
        //then
        resultActions.andExpect(status().isOk())
                .andDo(print());
    }
}