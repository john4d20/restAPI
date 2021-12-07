package com.example.restapi;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("employees")
public class EmployeeController {
    private EmployeeRepository employeeRepository;

    public EmployeeController(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @GetMapping()
    public List<Emplyoee> getAllEmployees(){
        return employeeRepository.findAll();
    }

    @GetMapping("/{id}")
    public Emplyoee getEmployeeById(@PathVariable Integer id) {
        return employeeRepository.findById(id);
    }

    @GetMapping(params = "{gender}")
    public List<Emplyoee> getEmployeeByGender(@PathVariable String gender){
        return employeeRepository.findByGender(gender);
    }
}
