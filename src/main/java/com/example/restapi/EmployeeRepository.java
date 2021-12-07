package com.example.restapi;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class EmployeeRepository {
    private List<Emplyoee> emplyoees = new ArrayList<>();

    public EmployeeRepository() {
        emplyoees.add(new Emplyoee(1,"john",20,"male",2000));
    }

    public List<Emplyoee> findAll() {
        return emplyoees;
    }

    public Emplyoee findById(Integer id) {
        return emplyoees.stream()
                .filter(emplyoee -> emplyoee.getId().equals(id))
                .findFirst()
                .orElseThrow(NoFoundEmployeeException::new);
    }

    public List<Emplyoee> findByGender(String gender) {
        return emplyoees.stream()
                .filter(emplyoee -> emplyoee.getGender().equals(gender))
                .collect(Collectors.toList());
    }
}
