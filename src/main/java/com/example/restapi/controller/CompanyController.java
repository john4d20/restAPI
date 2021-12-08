package com.example.restapi.controller;

import com.example.restapi.entity.Company;
import com.example.restapi.repository.CompanyRepository;
import com.example.restapi.entity.Employee;
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
    public Company getCompanyById(@PathVariable Integer id) {
        return companyRepository.findById(id);
    }

    @GetMapping("/{id}/employees")
    public List<Employee> getEmployeesByCompanyId(@PathVariable Integer id) {
        return companyRepository.findEmployeeById(id);
    }

    @GetMapping(params = {"page", "pageSize"})
    public List<Company> getCompanyByPage(@RequestParam Integer page, @RequestParam Integer pageSize) {
        return companyRepository.findByPage(page, pageSize);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Company createCompany(@RequestBody Company company){
        return companyRepository.create(company);
    }

    @PutMapping("/{id}")
    public Company editCompany(@PathVariable Integer id, @RequestBody Company updatedCompany){
        Company company = companyRepository.findById(id);
        if(updatedCompany.getCompanyName() != null){
            company.setCompanyName(updatedCompany.getCompanyName());}
        return companyRepository.save(id,updatedCompany);

    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public Company deleteCompany(@PathVariable Integer id){
        Company company = companyRepository.findById(id);
        return companyRepository.delete(company);
    }
}



