package com.example.restapi.entity;

import java.util.List;

public class Company {
    private Integer id;
    private String companyName;


    public Company(Integer id, String companyName) {
        this.id = id;
        this.companyName = companyName;

    }

    public Company() {

    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }



    public Integer getId() {
        return id;
    }

    public String getCompanyName() {
        return companyName;
    }

}
