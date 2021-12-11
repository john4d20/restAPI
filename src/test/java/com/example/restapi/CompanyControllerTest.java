package com.example.restapi;

import com.example.restapi.entity.Company;

import com.example.restapi.entity.Employee;
import com.example.restapi.repository.CompanyRepositoryNew;
import com.example.restapi.repository.EmployeeRepositoryNew;
import org.junit.jupiter.api.AfterEach;
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

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CompanyControllerTest {

    @Autowired
    CompanyRepositoryNew companyRepositoryNew;
    @Autowired
    MockMvc mockMvc;
    @Autowired
    EmployeeRepositoryNew employeeRepositoryNew;

    @BeforeEach
    void cleanRepository() {
        companyRepositoryNew.deleteAll();
    }

    @AfterEach
    void cleanRepositoryAfter() {
        companyRepositoryNew.deleteAll();
    }



    @Test
    void should_get_all_companies_when_perform_get_given_companies() throws Exception {
        //given

        Company company1 = new Company("1", "Spring",new ArrayList<>());
        Company company2 = new Company("2", "Spring2",new ArrayList<>());

        companyRepositoryNew.insert(company1);
        companyRepositoryNew.insert(company2);

        //when
        //then
        mockMvc.perform(MockMvcRequestBuilders.get("/companies"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id").isString())
                .andExpect(jsonPath("$[0].companyName").value("Spring"))
                .andExpect(jsonPath("$[1].id").isString())
                .andExpect(jsonPath("$[1].companyName").value("Spring2"));

    }

    @Test
    void should_get_company_when_perform_getById_given_company_and_id() throws Exception {
        //given

        Company company1 = new Company( "Spring",new ArrayList<>());
        Company company2 = new Company( "Spring2",new ArrayList<>());

        companyRepositoryNew.insert(company1);
        companyRepositoryNew.insert(company2);

        //when
        //then
        mockMvc.perform(MockMvcRequestBuilders.get("/companies/{id}", company1.getId()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.id").isString())
                .andExpect(jsonPath("$.companyName").value("Spring"));
    }

    @Test
    void should_get_employees_when_perform_get_given_company_id() throws Exception {
        //given
        Company company1 = new Company( "John Ltd");
        companyRepositoryNew.insert(company1);
        Company company2 = new Company( "John Company");
        companyRepositoryNew.insert(company2);
        Employee employeeJohn = new Employee("John", 20,"M", 20, company1.getId());
        employeeRepositoryNew.insert(employeeJohn);
        Employee employeeAnna = new Employee("Anna", 20,"F", 99999, company1.getId());
        employeeRepositoryNew.insert(employeeAnna);
        //when
        //then
        System.out.println(company1.getId());
        mockMvc.perform((MockMvcRequestBuilders.get(  "/companies/{id}/employees", company1.getId())))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").isString())
                .andExpect((jsonPath("$[0].name")).value("John"))
                .andExpect((jsonPath("$[0].age").value(20)))
                .andExpect((jsonPath("$[0].gender").value("M")));
    }

    @Test
    void should_get_all_companies_when_get_by_page_given_page_and_pageSize_and_company() throws Exception {
        Company company1 = new Company("1", "Spring",new ArrayList<>());
        Company company2 = new Company("2", "Spring2",new ArrayList<>());
        Company company3 = new Company("3", "Spring3",new ArrayList<>());

        companyRepositoryNew.insert(company1);
        companyRepositoryNew.insert(company2);
        companyRepositoryNew.insert(company3);

        String page = "1";
        String pageSize = "2";

        mockMvc.perform(MockMvcRequestBuilders.get("/companies?page="+page+"&pageSize="+pageSize))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    void should_return_company_when_perform_post_given_company() throws Exception {
        //given


        //when
        //then
        Company company = companyRepositoryNew.save(new Company("ABC Company", new ArrayList<>()));
        String companyAsJson = "{\n" +
                "        \"id\": 3,\n" +
                "        \"companyName\": \"hater3\"\n" +
                "    }";
        mockMvc.perform(MockMvcRequestBuilders.put("/companies/" + company.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(companyAsJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").isString())
                .andExpect(jsonPath("$.companyName").value("hater3"));
    }

    @Test
    void should_return_changed_company_when_perform_put_given_company_id() throws Exception {
        //given
        Company company1 = new Company( "Spring",new ArrayList<>());
        Company company2 = new Company( "Spring2",new ArrayList<>());
        Company company3 = new Company( "Spring3",new ArrayList<>());

        companyRepositoryNew.insert(company1);
        companyRepositoryNew.insert(company2);
        companyRepositoryNew.insert(company3);

        String company = "{\n" +
                "        \"id\": 1,\n" +
                "        \"companyName\": \"hater3\"\n" +
                "    }";

        //when
        //then
        mockMvc.perform(put("/companies/{id}", company1.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(company))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.companyName").value("hater3"));
    }

    @Test
    void should_delete_company_when_perform_delete_given_company_and_id() throws Exception {
        //given
        Company company1 = new Company( "Spring",new ArrayList<>());
        Company company2 = new Company( "Spring2",new ArrayList<>());
        Company company3 = new Company( "Spring3",new ArrayList<>());

        companyRepositoryNew.insert(company1);
        companyRepositoryNew.insert(company2);
        companyRepositoryNew.insert(company3);
        //when
        //then
        mockMvc.perform(MockMvcRequestBuilders.delete("/companies/{id}", company1.getId()))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    @Test
    void should_return_employee_when_perform_given_employee() throws Exception {

        String companies = "{\n" +
                "        \"companyName\": \"hater3\"\n" +
                "    }";

        mockMvc.perform(post("/companies")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(companies))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.companyName").value("hater3"));
    }



}
