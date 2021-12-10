package com.example.restapi;

import com.example.restapi.entity.Employee;
import com.example.restapi.repository.EmployeeRepository;
import com.example.restapi.repository.EmployeeRepositoryNew;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
public class EmployeeControllerTest {
    @Autowired
    MockMvc mockMvc;
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    EmployeeRepositoryNew employeeRepositoryNew;

    @BeforeEach
    void cleanRepository(){
        employeeRepositoryNew.deleteAll();
    }

    @AfterEach
    void cleanRepositoryAfter(){
        employeeRepositoryNew.deleteAll();
    }


    @Test
    void should_get_all_employees_when_perform_get_given_employees() throws Exception {
        //given
        Employee employee = new Employee("john",20,"male",1000,"61b1b8f9d63d36b42ef04399");
        employeeRepositoryNew.save(employee);

        mockMvc.perform(MockMvcRequestBuilders.get("/employees"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",hasSize(1)))
                .andExpect(jsonPath("$[0].id").isString())
                .andExpect(jsonPath("$[0].name").value("john"))
                .andExpect(jsonPath("$[0].age").value(20))
                .andExpect(jsonPath("$[0].gender").value("male"));


    }

    @Test
    void should_get_employee_when_perform_get_given_gender() throws Exception {
        //given
        Employee employee = new Employee("john",20,"male",1000,"61b1b8f9d63d36b42ef04399");
        employeeRepositoryNew.insert(employee);
        mockMvc.perform(MockMvcRequestBuilders.get("/employees?gender=male"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",hasSize(1)))
                .andExpect(jsonPath("$[0].id").isString())
                .andExpect(jsonPath("$[0].name").value("john"))
                .andExpect(jsonPath("$[0].age").value(20))
                .andExpect(jsonPath("$[0].gender").value("male"));

    }
    
    @Test
    void should_return_employee_when_perform_given_employee() throws Exception {
        //given
        String employee = "{\"id\": 1,\n" +
                "                \"name\": \"john\",\n" +
                "                \"age\": 20,\n" +
                "                \"gender\": \"male\",\n" +
                "                \"salary\": 1000}";

        mockMvc.perform(post("/employees")
                .contentType(MediaType.APPLICATION_JSON)
                .content(employee))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("john"))
                .andExpect(jsonPath("$.age").value(20))
                .andExpect(jsonPath("$.gender").value("male"));
        List<Employee> employeeList = employeeRepositoryNew.findAll();
        assertEquals(1, employeeList.size());
        assertEquals("john", employeeList.get(0).getName());
        assertEquals(20, employeeList.get(0).getAge());
        assertEquals(1000, employeeList.get(0).getSalary());
        assertEquals("male", employeeList.get(0).getGender());
    }

    @Test
    void should_return_employee_when_perform_get_given_employee_id() throws Exception {
        //given
        Employee employee = new Employee("john",20,"male",1000,"61b1b8f9d63d36b42ef04399");
        employeeRepositoryNew.insert(employee);

        mockMvc.perform(MockMvcRequestBuilders.get("/employees/{id}", employee.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").isString())
                .andExpect(jsonPath("$.name").value("john"))
                .andExpect(jsonPath("$.age").value(20))
                .andExpect(jsonPath("$.gender").value("male"));
    }

    @Test
    void should_return_employees_when_perform_get_given_page_and_page_size() throws Exception {
        //given
        employeeRepositoryNew.insert( new Employee("john",20,"male",1000,"61b1b8f9d63d36b42ef04399"));
        employeeRepositoryNew.insert( new Employee("john",20,"male",1000,"61b1b8f9d63d36b42ef04399"));
        employeeRepositoryNew.insert( new Employee("john",20,"male",1000,"61b1b8f9d63d36b42ef04399"));
        employeeRepositoryNew.insert( new Employee("john",20,"male",1000,"61b1b8f9d63d36b42ef04399"));
        employeeRepositoryNew.insert( new Employee("john",20,"male",1000,"61b1b8f9d63d36b42ef04399"));
        //when
        mockMvc.perform(MockMvcRequestBuilders.get("/employees?page=1&pageSize=3"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",hasSize(2)));
    }

    @Test
    void should_return_edited_employee_when_perform_put_given_updated_employee() throws Exception {
        //given
        Employee employee = new Employee("1","john",20,"male",1000,"61b1b8f9d63d36b42ef04399");
        employeeRepositoryNew.insert(employee);

        String updatedEmployee = "{\"id\": 1,\n" +
                "                \"name\": \"john\",\n" +
                "                \"age\": 18,\n" +
                "                \"gender\": \"male\",\n" +
                "                \"salary\": 1100}";

        mockMvc.perform(MockMvcRequestBuilders.put("/employees/{id}", employee.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updatedEmployee))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.age").value(18));
    }

    @Test
    void should_delete_when_perform_delete_given_employee_id() throws Exception {
        //given
        Employee employee = new Employee("john",20,"male",1000,"61b1b8f9d63d36b42ef04399");
        employeeRepositoryNew.save(employee);
        //when
        mockMvc.perform(MockMvcRequestBuilders.delete("/employees/{id}",employee.getId()))
                .andExpect(status().isNoContent());

        //then
        mockMvc.perform(MockMvcRequestBuilders.get("/employees"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",hasSize(0)));

    }




}
