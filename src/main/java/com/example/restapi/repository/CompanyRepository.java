package com.example.restapi.repository;

import com.example.restapi.exception.NoCompanyFoundException;
import com.example.restapi.entity.Company;
import com.example.restapi.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class CompanyRepository {
    @Autowired
    EmployeeRepositoryNew employeeRepositoryNew;

    private List<Company> companies = new ArrayList<>();


    public CompanyRepository() {
        companies.add(new Company("1","hater",new ArrayList<>()));
        companies.add(new Company("2","hater2",new ArrayList<>()));
    }

    public List<Company> findAll() {
        return companies;
    }

    public Company findById(Integer id) {
        return companies.stream()
                .filter(company -> company.getId().equals(id))
                .findFirst()
                .orElseThrow(NoCompanyFoundException::new);
    }

    public List<Employee> findEmployeesByCompanyId(String id) {
        return employeeRepositoryNew.findAllByCompanyId(id);
    }


    public List<Company> findByPage(Integer page, Integer pageSize) {
        return companies.stream()
                .skip((long) (page - 1)* pageSize )
                .limit(pageSize)
                .collect(Collectors.toList());
    }


    public Company create(Company company) {
        String id = String.valueOf(companies.size()+1);
        company.setId(id);
        companies.add(company);
        return company;
    }

    public Company save(Integer id, Company updatedCompany) {
        Company company = findById(id);
        companies.remove(company);
        companies.add(updatedCompany);
        return company;
    }

    public Company delete(Company company) {
        companies.remove(company);
        return company;
    }

    public void clearAll() {
        companies.clear();
    }


}






