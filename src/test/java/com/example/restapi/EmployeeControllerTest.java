package com.example.restapi;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;



import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
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
    //    get"/employees"
//    prepare data
//    send request
//    assertion
    @BeforeEach
    void cleanRepository(){
        employeeRepository.clearAll();
    }


    @Test
    void should_get_all_employees_when_perform_get_given_employees() throws Exception {
        //given
        Employee employee = new Employee(1,"john",20,"male",1000);
        employeeRepository.create(employee);

        mockMvc.perform(MockMvcRequestBuilders.get("/employees"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",hasSize(1)))
                .andExpect(jsonPath("$[0].id").isNumber())
                .andExpect(jsonPath("$[0].name").value("john"))
                .andExpect(jsonPath("$[0].age").value(20))
                .andExpect(jsonPath("$[0].gender").value("male"))
                .andExpect(jsonPath("$[0].salary").value(1000));

    }

    @Test
    void should_get_employee_when_perform_get_given_gender() throws Exception {
        //given
        Employee employee = new Employee(1,"john",20,"male",1000);
        employeeRepository.create(employee);
        mockMvc.perform(MockMvcRequestBuilders.get("/employees?gender=male"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",hasSize(1)))
                .andExpect(jsonPath("$[0].id").isNumber())
                .andExpect(jsonPath("$[0].name").value("john"))
                .andExpect(jsonPath("$[0].age").value(20))
                .andExpect(jsonPath("$[0].gender").value("male"))
                .andExpect(jsonPath("$[0].salary").value(1000));

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
                .andExpect(jsonPath("$.gender").value("male"))
                .andExpect(jsonPath("$.salary").value(1000));
        //then
    }

    @Test
    void should_return_employee_when_perform_get_given_employee_id() throws Exception {
        //given
        Employee employee = new Employee(1,"john",20,"male",1000);
        employeeRepository.create(employee);

        mockMvc.perform(MockMvcRequestBuilders.get("/employees/{id}", employee.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.name").value("john"))
                .andExpect(jsonPath("$.age").value(20))
                .andExpect(jsonPath("$.gender").value("male"))
                .andExpect(jsonPath("$.salary").value(1000));
    }

    @Test
    void should_return_employees_when_perform_get_given_page_and_page_size() throws Exception {
        //given
        employeeRepository.create( new Employee(1,"john",20,"male",1000));
        employeeRepository.create( new Employee(1,"john",20,"male",1000));
        employeeRepository.create( new Employee(1,"john",20,"male",1000));
        employeeRepository.create( new Employee(1,"john",20,"male",1000));
        employeeRepository.create( new Employee(1,"john",20,"male",1000));
        //when
        mockMvc.perform(MockMvcRequestBuilders.get("/employees?page=1&pageSize=3"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",hasSize(3)))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[2].id").value(3));
    }

    @Test
    void should_return_edited_employee_when_perform_put_given_updated_employee() throws Exception {
        //given
        Employee employee = new Employee(1,"john",20,"male",1000);
        employeeRepository.create(employee);

        String updatedEmployee = "{\"id\": 1,\n" +
                "                \"name\": \"john\",\n" +
                "                \"age\": 18,\n" +
                "                \"gender\": \"male\",\n" +
                "                \"salary\": 1100}";

        mockMvc.perform(MockMvcRequestBuilders.put("/employees/{id}", employee.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updatedEmployee))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.age").value(18))
                .andExpect(jsonPath("$.salary").value(1100));
    }

}
