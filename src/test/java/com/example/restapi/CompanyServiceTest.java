package com.example.restapi;

import com.example.restapi.entity.Company;
import com.example.restapi.repository.CompanyRepository;
import com.example.restapi.service.CompanyService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;

@ExtendWith(SpringExtension.class)
public class CompanyServiceTest {
    @Mock
    CompanyRepository mockCompanyRepository;

    @InjectMocks
    CompanyService companyService;

    @Test
    void should_return_all_employees_when_find_all_given_employees() {
        //given
        List<Company> companies = new ArrayList<>();
        given(mockCompanyRepository.findAll())
                .willReturn(companies);
        //when
        List<Company> actual = companyService.findAll();
        //then
        assertEquals(companies, actual);
    }

    @Test
    void should_get_company_when_perform_getById_given_company_and_id() throws Exception {
        //given
        List<Company> companies = new ArrayList<>();
        companies.add(new Company(1, "OOCL"));
        companies.add(new Company(2, "OOCL2"));

        given(mockCompanyRepository.findById(1))
                .willReturn(companies.get(0));

        //when
        Company actual = companyService.findById(1);
        //then
        assertEquals(companies.get(0), actual);
    }
}
