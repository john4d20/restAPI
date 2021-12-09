package com.example.restapi.repository;

import com.example.restapi.entity.Company;
import com.example.restapi.entity.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CompanyRepositoryNew extends MongoRepository <Company, String>{
    List<Employee> findAllById();
}
