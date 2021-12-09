package com.example.restapi.entity;

import java.util.List;

public class Company {
    private String id;
    private String companyName;


    public Company(String id, String companyName) {
        this.id = id;
        this.companyName = companyName;

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

}
