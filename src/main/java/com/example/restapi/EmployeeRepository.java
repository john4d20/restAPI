package com.example.restapi;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class EmployeeRepository {
    private List<Employee> employees = new ArrayList<>();

    public EmployeeRepository() {
        employees.add(new Employee(1,"john",20,"male",2000));
        employees.add(new Employee(2,"john2",20,"male",2000));
        employees.add(new Employee(3,"john3",20,"male",2000));
        employees.add(new Employee(4,"john4",20,"male",2000));
        employees.add(new Employee(5,"john5",20,"male",2000));
    }

    public List<Employee> findAll() {
        return employees;
    }

    public Employee findById(Integer id) {
        return employees.stream()
                .filter(employee -> employee.getId().equals(id))
                .findFirst()
                .orElseThrow(NoFoundEmployeeException::new);
    }

    public List<Employee> findByGender(String gender) {
        return employees.stream()
                .filter(employee -> employee.getGender().equals(gender))
                .collect(Collectors.toList());
    }

    public List<Employee> findByPage(Integer page, Integer pageSize){
        return employees.stream()
                .skip((long) (page - 1)* pageSize )
                .limit(pageSize)
                .collect(Collectors.toList());
    }

}
