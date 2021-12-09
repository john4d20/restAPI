package com.example.restapi.repository;



import com.example.restapi.entity.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepositoryNew extends MongoRepository<Employee,String> {
}
