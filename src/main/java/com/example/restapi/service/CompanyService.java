package com.example.restapi.service;

import com.example.restapi.entity.Company;
import com.example.restapi.entity.Employee;
import com.example.restapi.exception.NoCompanyFoundException;
import com.example.restapi.repository.CompanyRepositoryNew;
import com.example.restapi.repository.EmployeeRepositoryNew;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService {
    private CompanyRepositoryNew companyRepositoryNew;
    private EmployeeRepositoryNew employeeRepositoryNew;

    public CompanyService(CompanyRepositoryNew companyRepositoryNew,EmployeeRepositoryNew employeeRepositoryNew) {
        this.companyRepositoryNew = companyRepositoryNew;
        this.employeeRepositoryNew = employeeRepositoryNew;
    }

    public List<Company> findAll() {
        return companyRepositoryNew.findAll();
    }



    public List<Employee> findEmployeesByCompanyId(String companyId) {
        return employeeRepositoryNew.findAllByCompanyId(companyId);
    }




    public Company createCompany(Company company) {
        return companyRepositoryNew.insert(company);
    }

    public Company findById(String companyId) {
        return companyRepositoryNew.findById(companyId).orElseThrow(NoCompanyFoundException::new);
    }

    public Company add(Company requestCompany) {
        return this.companyRepositoryNew.save(requestCompany);
    }

    public Page<Company> findByPage(int page, int pageSize) {
        return companyRepositoryNew.findAll(PageRequest.of(page - 1, pageSize));
    }

    public Company update(String companyId, Company updateCompany) {
        if (companyRepositoryNew.existsById(companyId)) {
            updateCompany.setId(companyId);
            return companyRepositoryNew.save(updateCompany);
        }
        throw new NoCompanyFoundException();
    }

    public Company create(Company company) {
        return this.companyRepositoryNew.save(company);
    }


    public void delete(Company company) {
        companyRepositoryNew.deleteById(company.getId());
    }
}







