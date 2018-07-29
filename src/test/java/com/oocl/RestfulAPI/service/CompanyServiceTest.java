package com.oocl.RestfulAPI.service;

import com.oocl.RestfulAPI.entities.Company;
import com.oocl.RestfulAPI.entities.CompanyRepository;
import com.oocl.RestfulAPI.entities.Employee;
import com.oocl.RestfulAPI.services.CompanyService;
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
public class CompanyServiceTest {

    @Mock
    CompanyRepository repository;

    @Test
    public void should_return_the_hole_company_list(){
        //given
        CompanyService service = new CompanyService(repository);
        List<Company> companies = new ArrayList<>(
                Arrays.asList(new Company("hahha")));

        //when
        when(repository.findAll()).thenReturn(companies);
        //then
        assertThat(service.getCompanyList(), is(companies));
    }

    @Test
    public void should_return_the_company_with_id_is_1() {
        //given
        CompanyService service = new CompanyService(repository);
        Company company = new Company("hahh");

        //when
        when(repository.findById(1)).thenReturn(java.util.Optional.ofNullable(company));
        //then
        assertThat(service.getCompany(1), is(company));
    }

    @Test
    public void should_return_the_employees_of_company_1(){

        //given
        CompanyService service = new CompanyService(repository);
        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(new Employee(1,"sam",26,"male"));
        employeeList.add(new Employee(1,"sami",23,"female"));
        Company company1 = new Company(1,"nishu",employeeList);
        Company company2 = new Company(2,"shuia",employeeList);
        when(repository.findById(1)).thenReturn(java.util.Optional.ofNullable(company1));
        //when
        List<Employee> employees = service.getEmployeesByCompanyId(1);

        assertThat(employees,is(employeeList));
    }

//    @Test
//    public void should_return_the_first_2_companies_with_1_page_and_size_is_2(){
//
//        //given
//        CompanyService service = new CompanyService(repository);
//        List<Employee> employeeList = Arrays.asList(new Employee(1,"sam",26,"male"));
//        Company company1 = new Company("nishu",employeeList);
//        Company company2 = new Company("shuia",employeeList);
//        Company company3 = new Company("xixi",employeeList);
//        List <Company> companies = Arrays.asList(company1,company2);
//
//        //when
//        when(repository.findAll(new PageRequest(0, 2))).thenReturn((Page<Company>) companies);
//
//        assertThat(service.getCompaniesInPage(1,2),is(companies));
//    }

    @Test
    public void should_add_company_successfully(){

        //given
        CompanyService service = new CompanyService(repository);
        List<Employee> employeeList = Arrays.asList(new Employee(1,"sam",26,"male"));
        Company company = new Company(1,"nishu",employeeList);
        //when
        service.add(company);

        verify(repository).save(company);
        assertThat(service.add(company),is(true));
    }

    @Test
    public void should_update_company_info_successfully(){
        //given
        CompanyService service = new CompanyService(repository);
        Company company = new Company(1,"kuaie",Arrays.asList(new Employee(1,"hah",21,"female")));
        //when
        boolean key = service.updateCompany(1,company);
        //then
        verify(repository).save(company);
        assertThat(key,is(true));

    }

    @Test
    public void should_delete_the_company_successfully(){
        //given
        CompanyService service = new CompanyService(repository);
        Company company = new Company(1,"kuaie",Arrays.asList(new Employee(1,"hah",21,"female")));
        when(repository.findById(1)).thenReturn(java.util.Optional.ofNullable(company));
        //when

        boolean key = service.deleteCompany(1);
        //then
        verify(repository).delete(company);
        assertThat(key,is(true));
    }
}
