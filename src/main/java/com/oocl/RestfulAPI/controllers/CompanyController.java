package com.oocl.RestfulAPI.controllers;

import com.oocl.RestfulAPI.services.CompanyService;
import com.oocl.RestfulAPI.entities.Company;
import com.oocl.RestfulAPI.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping()
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @GetMapping("companies")
    public List<Company> getCompanyList() {
        List<Company> Companies = companyService.getCompanyList();
        return Companies;
    }

    @GetMapping("companies/{id}")
    public Company getCompanyById(@PathVariable int id) {
        Company company = companyService.getCompany(id);
        return company;
    }

    @GetMapping("companies/{id}/employees")
    public List<Employee> getEmployeesByCompanyId(@PathVariable int id) {
        List<Employee> employeeList = companyService.getEmployeesByCompanyId(id);
        return employeeList;
    }

    @GetMapping("companies/page/{pageNumber}/pageSize/{pageSize}")
    public List<Company> getCompaniesByPage(@PathVariable("pageNumber") int pageNumber, @PathVariable("pageSize") int pageSize) {
        List<Company> Companies = companyService.getCompaniesInPage(pageNumber, pageSize);
        return Companies;
    }

    @PostMapping(path = "companies", produces = MediaType.APPLICATION_JSON_VALUE)
    public void addCompany(@RequestBody Company company) {
        companyService.add(company);
    }

    @PutMapping("companies/{id}")
    public void updateCompany(@PathVariable int id, @RequestBody Company company) {
        companyService.updateCompany(id, company);
        System.out.println(company.getName());
    }

    @DeleteMapping("companies/{id}")
    public void deleteCompany(@PathVariable int id) {
        companyService.deleteCompany(id);
        System.out.println(id);
    }
}
