package com.example.restapi.service;

import com.example.restapi.entity.Employee;
import com.example.restapi.exception.NoFoundEmployeeException;
import com.example.restapi.repository.EmployeeRepository;
import com.example.restapi.repository.EmployeeRepositoryNew;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    private EmployeeRepository employeeRepository;
    private EmployeeRepositoryNew employeeRepositoryNew;

    public EmployeeService(EmployeeRepository employeeRepository, EmployeeRepositoryNew employeeRepositoryNew) {
        this.employeeRepository = employeeRepository;
        this.employeeRepositoryNew = employeeRepositoryNew;
    }

    public List<Employee> findAll() {
//        return employeeRepository.findAll();
        return employeeRepositoryNew.findAll();
    }

    public Employee edit(String id, Employee updatedEmployee) {
        Employee employee = employeeRepository.findById(id);
        if (updatedEmployee.getAge() != null) {   //  !.equals(0)
            employee.setAge(updatedEmployee.getAge());
        }
        if (updatedEmployee.getSalary() != null) {  //  !.equals(0)
            employee.setSalary(updatedEmployee.getSalary());
        }
        return employeeRepository.save(id, employee);
    }

    public Employee findById(String id)  {
        return employeeRepositoryNew.findById(id).orElseThrow(NoFoundEmployeeException::new);
//        return employeeRepository.findById(id);
    }

    public List<Employee> findByGender(String gender) {
        return employeeRepository.findByGender(gender);
    }

    public List<Employee> findByPage(int page, int pageSize) {
        return employeeRepository.findByPage(page,pageSize);
    }

    public Employee create(Employee employee) {
        return employeeRepository.create(employee);
    }

    public Employee delete(Employee employee) {
        return employeeRepository.delete(employee);
    }

    public Employee save(String id, Employee employee) {
        return employeeRepository.save(id, employee);
    }
}
