package com.example.restapi.service;

import com.example.restapi.entity.Company;
import com.example.restapi.entity.Employee;
import com.example.restapi.exception.NoFoundEmployeeException;
import com.example.restapi.repository.CompanyRepository;
import com.example.restapi.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CompanyService {
    private CompanyRepository companyRepository;

    public List<Company> findAll() {
        return companyRepository.findAll();
    }

    public Company editCompany(Integer id, Company updatedCompany) {
        Company company = companyRepository.findById(id);
        return companyRepository.save(id, company);
    }

    public List<Employee> findEmployeeById(Integer id) {
        return companyRepository.findEmployeeById(id);
    }


    public List<Company> findByPage(int page, int pageSize) {
        return companyRepository.findByPage(page,pageSize);
    }

    public Company createCompany(Company company) {
        return companyRepository.create(company);
    }
    public Company findById(int companyId) {
        return companyRepository.findById(companyId);
    }

}







