package es.upm.miw.apaw_practice.adapters.mongodb.company.daos;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.adapters.mongodb.company.entities.CompanyEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

@TestConfig
public class CompanyRepositoryIT {

    @Autowired
    private CompanyRepository companyRepository;

    @Test
    void testFindByCompanyname() {
        assertTrue(this.companyRepository.findByCompanyname("TechCorp").isPresent());
        CompanyEntity company = this.companyRepository.findByCompanyname("TechCorp").get();
        assertEquals("TechCorp", company.getCompanyname());
        assertEquals("New York", company.getLocation());
        assertNotNull(company.getDepartmentEntities());
    }
}