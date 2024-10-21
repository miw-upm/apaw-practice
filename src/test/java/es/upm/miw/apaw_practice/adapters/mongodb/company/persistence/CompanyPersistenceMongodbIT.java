package es.upm.miw.apaw_practice.adapters.mongodb.company.persistence;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.models.company.Company;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
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

    @Test
    void testUpdateIndustry() {
        String companyName = "TechCorp";
        String newIndustry = "Renewable Energy";

        this.companyPersistenceMongodb.updateIndustry(companyName, newIndustry);

        Company updatedCompany = this.companyPersistenceMongodb.findByCompanyname(companyName);
        assertNotNull(updatedCompany);
        assertEquals(newIndustry, updatedCompany.getIndustry());
    }
    @Test
    void testFindHighestExpenseAmountByLocation() {
        String location = "New York";
        BigDecimal highestExpenseAmount = this.companyPersistenceMongodb.findHighestExpenseAmountByLocation(location);

        assertNotNull(highestExpenseAmount);
        assertEquals(new BigDecimal("0"), highestExpenseAmount);
    }
    @Test
    void testFindManagementNamesByIndustryAndDescription() {
        String industry = "Technology";
        String description = "Office Supplies";


        List<String> managementNames = this.companyPersistenceMongodb.findManagementNamesByIndustryAndDescription(industry, description);


        assertNotNull(managementNames);
        assertFalse(managementNames.isEmpty());


        assertTrue(managementNames.contains("John Doe"));


        assertEquals(managementNames.size(), managementNames.stream().distinct().count());
    }



}
