package com.example.restapi;

import com.example.restapi.entity.Company;
import com.example.restapi.entity.Employee;
import com.example.restapi.repository.CompanyRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
public class CompanyControllerTest {
    @Autowired
    CompanyRepository companyRepository;
    @Autowired
    MockMvc mockMvc;

    @BeforeEach
    void cleanRepository() {
        companyRepository.clearAll();
    }

//    List<Employee> getEmployees() {
//        List<Employee> employees = new ArrayList<>();
//        employees.add(new Employee(1, "john", 19, "Male", 100, 1));
//        employees.add(new Employee(2, "joanne", 22, "Female", 200, 1));
//        return employees;
//    }

    @Test
    void should_get_all_companies_when_perform_get_given_companies() throws Exception {
        //given
//        List<Employee> employees = getEmployees();
        Company company1 = new Company(1, "Spring");
        Company company2 = new Company(2, "Spring2");

        companyRepository.create(company1);
        companyRepository.create(company2);

        //when
        //then
        mockMvc.perform(MockMvcRequestBuilders.get("/companies"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id").isNumber())
                .andExpect(jsonPath("$[0].companyName").value("Spring"))
                .andExpect(jsonPath("$[1].id").isNumber())
                .andExpect(jsonPath("$[1].companyName").value("Spring2"));

    }

    @Test
    void should_get_company_when_perform_getById_given_company_and_id() throws Exception {
        //given
//        List<Employee> employees = getEmployees();
        Company company1 = new Company(1, "Spring");
        Company company2 = new Company(2, "Spring2");

        companyRepository.create(company1);
        companyRepository.create(company2);

        //when
        //then
        mockMvc.perform(MockMvcRequestBuilders.get("/companies/{id}", company1.getId()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.companyName").value("Spring"));
    }

    @Test
    void should_get_all_employee_when_get_list_given_company_id() throws Exception {
        //given
        Company company1 = new Company(1, "Spring");

        companyRepository.findEmployeeById(1);
        //when`
        //then
        mockMvc.perform(MockMvcRequestBuilders.get("/companies/{id}/employees" , company1.getId()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$[0].id").isNumber())
                .andExpect(jsonPath("$[0].name").value("john"))
                .andExpect(jsonPath("$[0].age").value("20"))
                .andExpect(jsonPath("$[0].gender").value("male"))
                .andExpect(jsonPath("$[0].salary").value("2000"))
                .andExpect(jsonPath("$[0].companyId").value("1"));

    }

}
