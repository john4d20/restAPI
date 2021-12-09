package com.example.restapi;

import com.example.restapi.entity.Company;

import com.example.restapi.repository.CompanyRepository;
import com.example.restapi.repository.CompanyRepositoryNew;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CompanyControllerTest {
    @Autowired
    CompanyRepository companyRepository;
    @Autowired
    CompanyRepositoryNew companyRepositoryNew;
    @Autowired
    MockMvc mockMvc;

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
//        List<Employee> employees = getEmployees();
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

//    @Test
//    void should_get_all_employee_when_get_list_given_company_id() throws Exception {
//        //given
//        List employees = new ArrayList<>();
//        employees.add(new Employee("john",20,"male",1000,1));
//        Company company1 = new Company("Spring",employees);
//
//
//        CompanyRepositoryNew.save(company1);
//        //when`
//        //then
//        mockMvc.perform(MockMvcRequestBuilders.get("/companies/{id}/employees" , company1.getId()))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(jsonPath("$[0].name").value("john"))
//                .andExpect(jsonPath("$[0].age").value("20"))
//                .andExpect(jsonPath("$[0].gender").value("male"))
//                .andExpect(jsonPath("$[0].salary").value("1000"))
//                .andExpect(jsonPath("$[0].companyId").value("1"));
//    }

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

        companyRepository.create(company1);
        companyRepository.create(company2);
        companyRepository.create(company3);
        //when
        //then
        mockMvc.perform(MockMvcRequestBuilders.delete("/companies/{id}", company1.getId()))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    //todo Exception

}
