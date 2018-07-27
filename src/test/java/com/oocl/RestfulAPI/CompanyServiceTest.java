package com.oocl.RestfulAPI;

import com.oocl.RestfulAPI.CompaniesAPI.Company;
import com.oocl.RestfulAPI.CompaniesAPI.CompanyService;
import com.oocl.RestfulAPI.EmployeesAPI.Employee;
import com.oocl.RestfulAPI.EmployeesAPI.EmployeeService;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.mock;

public class CompanyServiceTest {
    List<Employee> employeeList = new ArrayList<>();
    List<Company> companies = new ArrayList<>();
    Employee employee1 = mock(Employee.class);
    Employee employee2 = mock(Employee.class);

    @Test
    public void should_return_the_hole_employee_list(){

        employeeList.add(employee1);
        employeeList.add(employee2);
        Company company1 = new Company(1,"nishu",employeeList);
        Company company2 = new Company(2,"shuia",employeeList);
        companies.add(company1);
        companies.add(company2);

        CompanyService companyService = new CompanyService(companies);
        assertThat(companyService.getCompanyList(),is(companies));
    }

    @Test
    public void should_return_the_company_with_id_is_1(){

        employeeList.add(employee1);
        employeeList.add(employee2);
        Company company1 = new Company(1,"nishu",employeeList);
        Company company2 = new Company(2,"shuia",employeeList);
        companies.add(company1);
        companies.add(company2);

        CompanyService companyService = new CompanyService(companies);
        assertThat(companyService.getCompany(1),is(company1));
    }

    @Test
    public void should_return_the_employees_of_company_1(){

        employeeList.add(employee1);
        employeeList.add(employee2);
        Company company1 = new Company(1,"nishu",employeeList);
        Company company2 = new Company(2,"shuia",employeeList);
        companies.add(company1);
        companies.add(company2);

        CompanyService companyService = new CompanyService(companies);
        assertThat(companyService.getEmployeesByCompanyId(1),is(employeeList));
    }

    @Test
    public void should_return_the_first_2_companies_with_1_page_and_size_is_2(){

        employeeList.add(employee1);
        employeeList.add(employee2);

        Company company1 = new Company(1,"nishu",employeeList);
        Company company2 = new Company(2,"shuia",employeeList);
        Company company3 = new Company(3,"xixi",employeeList);
        companies.add(company1);
        companies.add(company2);
        companies.add(company3);

        CompanyService companyService = new CompanyService(companies);
        companies.remove(company3);

        assertThat(companyService.getCompaniesInPage(1,2),is(companies));
    }

    @Test
    public void should_return_should_return_the_3_companies_with_add_3(){

        Company company1 = new Company(1,"nishu",employeeList);
        Company company2 = new Company(2,"shuia",employeeList);
        Company company3 = new Company(3,"xixi",employeeList);
        companies.add(company1);
        companies.add(company2);

        CompanyService companyService = new CompanyService(companies);
        companies.add(company3);

        assertThat(companyService.getCompanyList(),is(companies));
    }

    @Test
    public void should_return_the_new_companies_info_with_update_1(){

        Company company1 = new Company(1,"nishu",employeeList);
        Company company2 = new Company(2,"shuia",employeeList);
        Company company3 = new Company(0,"wogaile",employeeList);
        companies.add(company1);
        companies.add(company2);

        CompanyService companyService = new CompanyService(companies);
        companyService.updateCompany(2,company3);
        company3.setId(2);
        assertThat(companyService.getCompany(2),is(company3));
    }

    @Test
    public void should_delete_the_company_info_with_delete_3(){
        Company company1 = new Company(1,"nishu",employeeList);
        Company company2 = new Company(2,"shuia",employeeList);
        Company company3 = new Company(0,"wogaile",employeeList);
        companies.add(company1);
        companies.add(company2);
        companies.add(company3);
        CompanyService companyService = new CompanyService(companies);
        companyService.deleteCompany(3);
        companies.remove(company3);
        assertThat(companyService.getCompanyList(),is(companies));
    }
}
