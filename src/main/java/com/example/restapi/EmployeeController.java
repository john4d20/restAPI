package com.example.restapi;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("employees")
public class EmployeeController {
    private EmployeeRepository employeeRepository;

    public EmployeeController(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @GetMapping()
    public List<Employee> getAllEmployees(){
        return employeeRepository.findAll();
    }

    @GetMapping("/{id}")
    public Employee getEmployeeById(@PathVariable Integer id) {
        return employeeRepository.findById(id);
    }

    @GetMapping(params = "{gender}")
    public List<Employee> getEmployeeByGender(@PathVariable String gender){
        return employeeRepository.findByGender(gender);
    }


    @GetMapping(params = {"page", "pageSize"})
    public List<Employee> getEmployeeByPage(@RequestParam Integer page, @RequestParam Integer pageSize) {
        return employeeRepository.findByPage(page, pageSize);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Employee createEmployee(@RequestBody Employee employee){
        return employeeRepository.create(employee);
    }
    
    @PutMapping("/{id}")
    public Employee editEmployee(@PathVariable Integer id, @RequestBody Employee updatedEmployee){
        Employee employee = employeeRepository.findById(id);
        if(updatedEmployee.getAge() != null){
            employee.setAge(updatedEmployee.getAge());}
        if(updatedEmployee.getId() != null){}{
            employee.setSalary(updatedEmployee.getSalary());
        }
        return employeeRepository.save(id,employee);
        
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public Employee deleteEmployee(@PathVariable Integer id){
        Employee employee = employeeRepository.findById(id);
        return employeeRepository.delete(employee);
    }



}
