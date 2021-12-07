package com.example.restapi;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class CompanyRepository {
    private List<Company> companies = new ArrayList<>();

    private List<Employee> employees = Arrays.asList(new Employee(1,"john",20,"male",2000),
            new Employee(2,"john2",20,"male",2000));


    public CompanyRepository() {
        companies.add(new Company(1,"hater",employees));
        companies.add(new Company(2,"hater2",employees));
    }

    public List<Company> findAll() {
        return companies;
    }
}






