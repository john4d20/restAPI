package com.example.restapi.service;

import com.example.restapi.entity.Company;
import com.example.restapi.entity.Employee;
import com.example.restapi.exception.NoCompanyFoundException;
import com.example.restapi.exception.NoFoundEmployeeException;
import com.example.restapi.repository.CompanyRepository;
import com.example.restapi.repository.CompanyRepositoryNew;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService {
    private CompanyRepositoryNew companyRepositoryNew;

    public CompanyService(CompanyRepositoryNew companyRepositoryNew) {
        this.companyRepositoryNew = companyRepositoryNew;
    }

    public List<Company> findAll() {
        return companyRepositoryNew.findAll();
    }

    public Company editCompany(String id, Company updatedCompany) {
        Company company = findById(id);
        if (updatedCompany.getCompanyName() != null) {
            company.setCompanyName(updatedCompany.getCompanyName());
        }
        return companyRepositoryNew.save(company);

    }


//    public List<Employee> findEmployeesByCompanyId(Integer id) {
//        return companyRepositoryNew.findEmployeesByCompanyId(id);
//
//    }


    public List<Company> findByPage(int page, int pageSize) {
        return companyRepositoryNew.findAll(PageRequest.of(page, pageSize)).getContent();

    }

    public Company createCompany(Company company) {
        return companyRepositoryNew.insert(company);
    }
    public Company findById(String companyId) {
        return companyRepositoryNew.findById(companyId).orElseThrow(NoCompanyFoundException::new);
    }

}







