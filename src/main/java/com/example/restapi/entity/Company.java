package com.example.restapi.entity;

import java.util.List;

public class Company {
    private String id;
    private String companyName;
    private List<String> employees;


    public Company(String id, String companyName,List<String> employees) {
        this.id = id;
        this.companyName = companyName;
        this.employees = employees;

    }

    public Company() {

    }

    public void setId(String id) {
        this.id = id;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }



    public String getId() {
        return id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public List<String> getEmployees() {
        return employees;
    }

    public void setEmployees(List<String> employees) {
        this.employees = employees;
    }
}
