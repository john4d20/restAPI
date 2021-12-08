package com.example.restapi.repository;

import com.example.restapi.exception.NoFoundEmployeeException;
import com.example.restapi.entity.Company;
import com.example.restapi.entity.Employee;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class CompanyRepository {
    private List<Company> companies = new ArrayList<>();

    private List<Employee> employees = Arrays.asList(new Employee(1,"john",20,"male",2000,1),
            new Employee(2,"john2",20,"male",2000,1));


    public CompanyRepository() {
        companies.add(new Company(1,"hater",employees));
        companies.add(new Company(2,"hater2",employees));
    }

    public List<Company> findAll() {
        return companies;
    }

    public Company findById(Integer id) {
        return companies.stream()
                .filter(company -> company.getId().equals(id))
                .findFirst()
                .orElseThrow(NoFoundEmployeeException::new);
    }

    public List<Employee> findEmployeeById(Integer id) {
        Company companyById = companies.stream()
                .filter(company -> company.getId().equals(id))
                .findFirst()
                .orElseThrow(NoFoundEmployeeException::new);
        return companyById.getEmployees();
    }

    public List<Company> findByPage(Integer page, Integer pageSize) {
        return companies.stream()
                .skip((long) (page - 1)* pageSize )
                .limit(pageSize)
                .collect(Collectors.toList());
    }


    public Company create(Company company) {
        Integer nextId = employees.stream()
                .mapToInt(Employee::getId)
                .max()
                .orElse(0)+1;
        company.setId(nextId);
        if (companies.add(company)){
            return company;
        }
        return null;
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
}






