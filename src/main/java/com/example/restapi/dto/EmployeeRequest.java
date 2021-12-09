package com.example.restapi.dto;

public class EmployeeRequest {
    private String name;
    private Integer age;
    private String gender;
    private Integer salary;
    private String companyId;

    public EmployeeRequest(String name) {
        this.name = name;
    }
}
