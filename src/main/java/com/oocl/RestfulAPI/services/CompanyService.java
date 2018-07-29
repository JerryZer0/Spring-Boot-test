package com.oocl.RestfulAPI.services;

import com.oocl.RestfulAPI.entities.Company;
import com.oocl.RestfulAPI.entities.CompanyRepository;
import com.oocl.RestfulAPI.entities.Employee;
import com.oocl.RestfulAPI.entities.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CompanyService {

    private CompanyRepository companyRepository;

    @Autowired
    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public List<Company> getCompanyList() {
        List<Company> companies = companyRepository.findAll();
        return companies;
    }

    public Company getCompany(int id) {
        Company company = companyRepository.findById(id).get();
        return company;
    }

    public List<Employee> getEmployeesByCompanyId(int id) {
        Company company = companyRepository.findById(id).get();
        List<Employee> employeeList = company.getEmployeeList();
        return employeeList;
    }

    public List<Company> getCompaniesInPage(int pageNumber, int pageSize) {
        Page<Company> getByPage = companyRepository.findAll(new PageRequest(0, 2));
        return (List<Company>) getByPage;
    }

    public void add(Company company) {
        company.getEmployeeList().stream().forEach(employee ->
                employee.setCompany(company));
        companyRepository.save(company);
    }

    public void updateCompany(int id, Company company1) {
        Company company = companyRepository.findById(id).get();
        companyRepository.save(company);
    }

    public void deleteCompany(int id) {
        Company company = companyRepository.findById(id).get();
        companyRepository.delete(company);
    }
}
