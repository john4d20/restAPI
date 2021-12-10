package com.example.restapi.repository;

import com.example.restapi.exception.NoFoundEmployeeException;
import com.example.restapi.entity.Employee;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Repository
public class EmployeeRepository {
    private List<Employee> employees = new ArrayList<>();

    public EmployeeRepository() {
        employees.add(new Employee("1","john",20,"male",2000,"61b1b8f9d63d36b42ef04399"));
        employees.add(new Employee("2","john2",20,"male",2000,"61b1b8f9d63d36b42ef04399"));
        employees.add(new Employee("3","john3",20,"male",2000,"61b1b8f9d63d36b42ef04399"));
        employees.add(new Employee("4","john4",20,"male",2000,"61b1b8f9d63d36b42ef04399"));
        employees.add(new Employee("5","john5",20,"male",2000,"61b1b8f9d63d36b42ef04399"));
    }

    public List<Employee> findAll() {
        return employees;
    }

    public Employee findById(String id) {
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

    public Employee create(Employee employee) {
        String id = String.valueOf(employees.size()+1);
        employee.setId(id);
        employees.add(employee);
        return employee;
    }

    public Employee save(String id, Employee updatedEmployee) {
        Employee employee = findById(id);
        employees.remove(employee);
        employees.add(updatedEmployee);
        return employee;
    }

    public void delete(Employee employee) {
        employees.remove(employee);

    }

    public void clearAll() {
        employees.clear();;
    }

    public List<Employee> findByCompanyId(String id) {
        return employees.stream().filter(employee -> employee.getCompanyId().equals(id)).collect(Collectors.toList());
    }
}
