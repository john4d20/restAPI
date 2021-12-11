package com.example.restapi;

import com.example.restapi.entity.Employee;
import com.example.restapi.repository.EmployeeRepositoryNew;
import com.example.restapi.service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(SpringExtension.class)
public class EmployeeServiceTest {

    @Mock
    EmployeeRepositoryNew mockEmployeeRepositoryNew;
    @InjectMocks
    EmployeeService employeeService;

    @Test
    public void should_return_all_employees_when_get_given_employees() {
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee("1", "Terence", 29, "Male", 66666,"61b1b8f9d63d36b42ef04399"));
        given(mockEmployeeRepositoryNew.findAll())
                .willReturn(employees);

        List<Employee> actual = employeeService.findAll();

        assertEquals(employees, actual);
    }


    @Test
    public void should_return_updated_employee_when_edit_given_updated_employee() {
        Employee employee = new Employee("1", "Terence", 29, "Male", 66666,"61b1b8f9d63d36b42ef04399");
        Employee updatedEmployee = new Employee("1", "Jooo", 19, "Female", 18888,"61b1b8f9d63d36b42ef04399");
        given(mockEmployeeRepositoryNew.findById(any()))
                .willReturn(java.util.Optional.of(employee));

        given(mockEmployeeRepositoryNew.save(any(Employee.class)))
                .willReturn(employee);

        Employee actual = employeeService.edit(employee.getId(),updatedEmployee);
        assertEquals(employee, actual);
    }

    @Test
    public void should_return_employee_when_get_given_ID() {
        Employee employee = new Employee("1", "Terence", 29, "Male", 66666,"61b1b8f9d63d36b42ef04399");
        given(mockEmployeeRepositoryNew.findById("1"))
                .willReturn(java.util.Optional.of(employee));
        Employee actual = employeeService.findById(employee.getId());
        assertEquals(employee, actual);
    }

    @Test
    public void should_return_employee_when_get_given_gender() {
        Employee employee = new Employee("1", "Terence", 29, "Male", 66666,"61b1b8f9d63d36b42ef04399");
        List<Employee> employees = Collections.singletonList(employee);
        given(mockEmployeeRepositoryNew.findAllByGender("Male"))
                .willReturn(employees);

        List<Employee> actual = employeeService.findByGender(employee.getGender());
        assertEquals(employees, actual);
    }

    @Test
    public void should_return_employees_when_get_given_page_and_page_size() {
        List<Employee> employees = new ArrayList<>();
        Employee firstEmployee = new Employee("1", "jojo", 29, "Male", 1,"61b1b8f9d63d36b42ef04399");
        Employee secondEmployee = new Employee("2", "john", 30, "Male", 66666,"61b1b8f9d63d36b42ef04399");
        employees.add(firstEmployee);
        employees.add(secondEmployee);
        Pageable pageable =  PageRequest.of(1,2);
        given(mockEmployeeRepositoryNew.findAll(pageable))
                .willReturn(new PageImpl<>(employees, PageRequest.of(1, 2), 1));

        List<Employee> actual = employeeService.findByPage(1,2);
        assertEquals("jojo",actual.get(0).getName());
        assertEquals(29,actual.get(0).getAge());
        assertEquals("Male",actual.get(0).getGender());
        assertEquals(1,actual.get(0).getSalary());
    }

    @Test
    public void should_return_new_employee_when_post_given_new_employee() {
        Employee employee = new Employee("1", "Terence", 29, "Male", 66666,"61b1b8f9d63d36b42ef04399");
        given(mockEmployeeRepositoryNew.insert(employee))
                .willReturn(employee);

        Employee actual = employeeService.create(employee);
        assertEquals(employee, actual);
    }

    @Test
    public void should_delete_when_delete_given_employee() {
        Employee employee = new Employee("1", "Terence", 29, "Male", 66666,"61b1b8f9d63d36b42ef04399");

        employeeService.create(employee);
        //when
        employeeService.delete("1");
        //then
        verify(mockEmployeeRepositoryNew, times(1)).deleteById("1");
    }
}
