package com.example.restapi.entity;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.List;

@Document
public class Company {
    @MongoId(FieldType.OBJECT_ID)
    private String id;
    private String companyName;
    private List<Employee> employees;


    public Company(String id, String companyName, List<Employee> employees) {
        this.id = id;
        this.companyName = companyName;
        this.employees = employees;

    }

    public Company( String companyName) {
        this.companyName = companyName;

    }

    public Company() {

    }

    public Company(String companyName, List<Employee> employees) {
        this.companyName = companyName;
        this.employees = employees;
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

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }
}
