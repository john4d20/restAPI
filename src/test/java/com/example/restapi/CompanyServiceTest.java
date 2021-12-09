package com.example.restapi;

import com.example.restapi.entity.Company;
import com.example.restapi.entity.Employee;
import com.example.restapi.repository.CompanyRepository;
import com.example.restapi.repository.CompanyRepositoryNew;
import com.example.restapi.service.CompanyService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;


@ExtendWith(SpringExtension.class)
public class CompanyServiceTest {
    private List<Employee> getEmployees() {
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee("1", "john", 22, "male", 1000, 1));
        employees.add(new Employee("2", "john", 22, "male", 1000, 1));
        return employees;
    }
    @Mock
    CompanyRepository mockCompanyRepository;

    @Mock
    CompanyRepositoryNew mockCompanyRepositoryNew;


    @InjectMocks
    CompanyService companyService;

    @Test
    void should_return_all_employees_when_find_all_given_employees() {
        //given
        List<Company> companies = new ArrayList<>();
        companies.add(new Company("1","company",new ArrayList<>()));
        given(mockCompanyRepositoryNew.findAll())
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
        companies.add(new Company("1", "OOCL",new ArrayList<>()));
        companies.add(new Company("2", "OOCL2",new ArrayList<>()));

        given(mockCompanyRepositoryNew.findById("1"))
                .willReturn(java.util.Optional.ofNullable(companies.get(0)));

        //when
        Company actual = companyService.findById("1");
        //then
        assertEquals(companies.get(0), actual);

    }


    @Test
    void should_return_specific_company_employee_list_when_get_company_employee_list_given_companies_company_id() {
        //given
        Company company = new Company("1","My Company", new ArrayList<>());
        List<String> expected = new ArrayList<>();
        when(mockCompanyRepositoryNew.findById("1")).thenReturn(java.util.Optional.of(company));

        companyService.add(new Company("2","My Company", expected));

        //when
        final List<String> actual = companyService.findEmployeesByCompanyId("1");

        //then
        assertEquals(expected, actual);
    }



    @Test
    void should_return_company_when_perform_post_given_company() throws Exception {
        //given
        Company newCompany = new Company("3", "OOCL3",new ArrayList<>());
        given(mockCompanyRepositoryNew.insert(newCompany))
                .willReturn(newCompany);
        //when
        Company actual = companyService.createCompany(newCompany);
        //then
        assertEquals(newCompany, actual);
    }

    @Test
    void should_return_first_2_companies_when_get_companies_by_page_given_companies_page1_pageSize2() {
        //given

        Company company1 = new Company("1","My Company1", new ArrayList<>());
        Company company2 = new Company("2","My Company2", new ArrayList<>());

        companyService.add(company1);
        companyService.add(company2);
        companyService.add(new Company("3","My Company2", new ArrayList<>()));

        final Page<Company> expected = new PageImpl<>(Arrays.asList(company1, company2));

        when(mockCompanyRepositoryNew.findAll(PageRequest.of(0, 2))).thenReturn(expected);
        //when
        final Page<Company> actual = companyService.findByPage(1,2);

        //then
        assertEquals(expected, actual);
    }

    @Test
    void should_return_updated_employees_when_get_employee_given_employees_employee_id() {
        //given
        Company company = new Company("1","My New Company1", new ArrayList<>());
        when(mockCompanyRepositoryNew.existsById("1")).thenReturn(true);
        when(mockCompanyRepositoryNew.save(company)).thenReturn(company);

        //when
        final Company actual = companyService.update("1", company);

        //then
        assertEquals(company, actual);
    }

}
