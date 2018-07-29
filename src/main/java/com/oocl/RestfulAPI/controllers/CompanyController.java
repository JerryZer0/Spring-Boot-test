package com.oocl.RestfulAPI.controllers;

import com.oocl.RestfulAPI.entities.Employee;
import com.oocl.RestfulAPI.services.CompanyService;
import com.oocl.RestfulAPI.entities.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping()
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @GetMapping(path = "companies", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Company> getCompanyList() {
        List<Company> Companies = companyService.getCompanyList();
        return Companies;
    }

    @GetMapping(path = "companies/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public CompanyDTO getCompanyById(@PathVariable int id) {
        Company company = companyService.getCompany(id);
        return new CompanyDTO(company);
    }

    @GetMapping(path = "companies/{id}/employees", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Employee> getEmployeesByCompanyId(@PathVariable int id) {
        List<Employee> employeeList = companyService.getEmployeesByCompanyId(id);
        return employeeList;
    }

    @GetMapping(path = "companies/page/{pageNumber}/pageSize/{pageSize}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Company> getCompaniesByPage(@PathVariable("pageNumber") int pageNumber, @PathVariable("pageSize") int pageSize) {
        List<Company> Companies = companyService.getCompaniesInPage(pageNumber, pageSize);
        return Companies;
    }

    @PostMapping(path = "companies", produces = MediaType.APPLICATION_JSON_VALUE)
    public void addCompany(@RequestBody Company company) {
        companyService.add(company);
    }

    @PutMapping(path = "companies/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public void updateCompany(@PathVariable int id, @RequestBody Company company) {
        companyService.updateCompany(id, company);
        System.out.println(company.getName());
    }

    @DeleteMapping(path = "companies/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public void deleteCompany(@PathVariable int id) {
        companyService.deleteCompany(id);
        System.out.println(id);
    }
}
