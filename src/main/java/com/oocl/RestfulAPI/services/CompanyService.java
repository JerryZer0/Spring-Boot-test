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
        List<Company> getByPage = (List<Company>) companyRepository.findAll(new PageRequest(0, 2));
        return getByPage;
    }

    public boolean add(Company company) {
        company.getEmployeeList().stream().forEach(employee ->
                employee.setCompany(company));
        companyRepository.save(company);
        return true;
    }

    public boolean updateCompany(int id, Company company1) {
        //Company company = companyRepository.findById(id).get();
        company1.setId(id);
        companyRepository.save(company1);
        return true;
    }

    public boolean deleteCompany(int id) {
        Company company = companyRepository.findById(id).get();
        companyRepository.delete(company);
        return true;
    }
}
