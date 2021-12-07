package com.example.restapi;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class EmployeeRepository {
    private List<Emplyoee> emplyoees = new ArrayList<>();

    public EmployeeRepository() {
        emplyoees.add(new Emplyoee(1,"john",20,"male",2000));
    }

    public List<Emplyoee> findAll() {
        return emplyoees;
    }
}
