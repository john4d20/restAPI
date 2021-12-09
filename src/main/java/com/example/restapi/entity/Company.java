package com.example.restapi.entity;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.ArrayList;
import java.util.List;

@Document
public class Company {
    @MongoId(FieldType.OBJECT_ID)
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

    public Company(String companyName, List<String> employees) {
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

    public List<String> getEmployees() {
        return employees;
    }

    public void setEmployees(List<String> employees) {
        this.employees = employees;
    }
}
