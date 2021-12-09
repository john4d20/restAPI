package com.example.restapi;

import com.example.restapi.entity.Employee;
import com.example.restapi.repository.EmployeeRepository;
import com.example.restapi.service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

@ExtendWith(SpringExtension.class)
public class EmployeeServiceTest {
    @Mock
    EmployeeRepository mockEmployeeRepository;
    @InjectMocks
    EmployeeService employeeService;

    @Test
    public void should_return_all_employees_when_get_given_employees() {
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee("1", "Terence", 29, "Male", 66666,1));
        given(mockEmployeeRepository.findAll())
                .willReturn(employees);

        List<Employee> actual = employeeService.findAll();

        assertEquals(employees, actual);
    }

//    TODO delete set , and employee -> updatedEmployee
    @Test
    public void should_return_updated_employee_when_edit_given_updated_employee() {
        Employee employee = new Employee("1", "Terence", 29, "Male", 66666,1);
        Employee updatedEmployee = new Employee("1", "Jooo", 19, "Female", 18888,1);
        given(mockEmployeeRepository.findById(any()))
                .willReturn(employee);
        employee.setAge(updatedEmployee.getAge());
        employee.setSalary(updatedEmployee.getSalary());
        given(mockEmployeeRepository.save(any(),any(Employee.class)))
                .willReturn(employee);

        Employee actual = employeeService.edit(employee.getId(),updatedEmployee);
        assertEquals(employee, actual);
    }

    @Test
    public void should_return_employee_when_get_given_ID() {
        Employee employee = new Employee("1", "Terence", 29, "Male", 66666,1);
        given(mockEmployeeRepository.findById("1"))
                .willReturn(employee);
        Employee actual = employeeService.findById(employee.getId());
        assertEquals(employee, actual);
    }

    @Test
    public void should_return_employee_when_get_given_gender() {
        Employee employee = new Employee("1", "Terence", 29, "Male", 66666,1);
        List<Employee> employees = Collections.singletonList(employee);
        given(mockEmployeeRepository.findByGender("Male"))
                .willReturn(employees);

        List<Employee> actual = employeeService.findByGender(employee.getGender());
        assertEquals(employees, actual);
    }

    @Test
    public void should_return_employees_when_get_given_page_and_page_size() {
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee("1", "Terence", 29, "Male", 66666,1));
        employees.add(new Employee("2", "Terence", 30, "Male", 66666,1));
        employees.add(new Employee("3", "Terence", 31, "Male", 66666,1));
        given(mockEmployeeRepository.findByPage(0,3))
                .willReturn(employees);

        List<Employee> actual = employeeService.findByPage(0,3);
        assertEquals(employees, actual);
    }

    @Test
    public void should_return_new_employee_when_post_given_new_employee() {
        Employee employee = new Employee("1", "Terence", 29, "Male", 66666,1);
        given(mockEmployeeRepository.create(employee))
                .willReturn(employee);

        Employee actual = employeeService.create(employee);
        assertEquals(employee, actual);
    }

    @Test
    public void should_delete_when_delete_given_employee() {
        Employee employee = new Employee("1", "Terence", 29, "Male", 66666,1);
        mockEmployeeRepository.create(employee);
        mockEmployeeRepository.delete(employee);

        assertEquals(mockEmployeeRepository.findAll().size(), 0);
    }
}
