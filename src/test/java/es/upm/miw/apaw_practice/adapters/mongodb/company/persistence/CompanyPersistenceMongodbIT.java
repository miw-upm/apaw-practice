package es.upm.miw.apaw_practice.adapters.mongodb.company.persistence;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.models.company.Company;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

@TestConfig
public class CompanyPersistenceMongodbIT {

    @Autowired
    private CompanyPersistenceMongodb companyPersistenceMongodb;

    @Test
    void testFindByCompanynameNotFound() {
        assertThrows(NotFoundException.class, () -> this.companyPersistenceMongodb.findByCompanyname("NonExistentCompany"));
    }

    @Test
    void testFindByCompanyname() {
        Company company = this.companyPersistenceMongodb.findByCompanyname("TechCorp");
        assertNotNull(company);
        assertEquals("TechCorp", company.getCompanyname());
        assertEquals("New York", company.getLocation());
        assertNotNull(company.getDepartments());
    }
}