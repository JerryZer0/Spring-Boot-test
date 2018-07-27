package com.oocl.RestfulAPI.CompaniesAPI;

import com.oocl.RestfulAPI.EmployeesAPI.Employee;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CompanyService {

    List<Company> companies = new ArrayList<>();
    List<Employee> employeeList = new ArrayList<>();
    Employee employee1 = new Employee(1, "小明", 20, "male");
    Employee employee2 = new Employee(2, "小红", 18, "female");

    public CompanyService(List<Company> companies) {
        this.companies = companies;
    }

    public CompanyService() {
        employeeList.add(employee1);
        employeeList.add(employee2);
        Company company1 = new Company(1,"haha",employeeList);
        Company company2 = new Company(2,"heh",employeeList);
        Company company3 = new Company(2,"xixi",employeeList);
        companies.add(company1);
        companies.add(company2);
        companies.add(company3);
    }

    public List<Company> getCompanyList() {
        return this.companies;
    }

    public Company getCompany(int id) {
        Company company = companies.get(id-1);
        return company;
    }

    public List<Employee> getEmployeesByCompanyId(int id) {
        List<Employee> employeeList = getCompany(id).getEmployeeList();
        return employeeList;
    }

    public List<Company> getCompaniesInPage(int pageNumber, int pageSize) {
        int size = companies.size();
        int begin = (pageNumber - 1) * pageSize;
        List<Company> getByPage = new ArrayList<>();
        for (int i = begin; i < size && ((i - begin) < pageSize); i++) {
            getByPage.add(companies.get(i));
        }
        return getByPage;
    }

    public void add(Company company) {
        companies.add(company);
    }

    public void updateCompany(int id, Company company) {
        Company companyOri = getCompany(id);
        if (company.getName() == null) {
            company.setName(companyOri.getName());
        }
        company.setId(companyOri.getId());
        companies.remove(companyOri);
        companies.add(company);
    }

    public void deleteCompany(int id) {
        companies.remove(id-1);
    }
}
