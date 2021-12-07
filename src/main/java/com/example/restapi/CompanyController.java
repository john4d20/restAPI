package com.example.restapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("companies")
public class CompanyController {
    CompanyRepository companyRepository;

//    @Autowired(required = false)
    public CompanyController(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @GetMapping
    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    @GetMapping("/{id}")
    public Company getEmployeeById(@PathVariable Integer id) {
        return companyRepository.findById(id);
    }

    @GetMapping("/{id}/employees")
    public List<Employee> getEmployeesById(@PathVariable Integer id) {
        return companyRepository.findEmployeeById(id);
    }

    @GetMapping(params = {"page", "pageSize"})
    public List<Company> getEmployeeByPage(@RequestParam Integer page, @RequestParam Integer pageSize) {
        return companyRepository.findByPage(page, pageSize);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Company createEmployee(@RequestBody Company company){
        return companyRepository.create(company);
    }

    @PutMapping("/{id}")
    public Company editEmployee(@PathVariable Integer id, @RequestBody Company updatedCompany){
        Company company = companyRepository.findById(id);
        if(updatedCompany.getCompanyName() != null){
            company.setCompanyName(updatedCompany.getCompanyName());}
        if(updatedCompany.getEmployees() != null){}{
            company.setEmployees(updatedCompany.getEmployees());
        }
        return companyRepository.save(id,updatedCompany);

    }
}



