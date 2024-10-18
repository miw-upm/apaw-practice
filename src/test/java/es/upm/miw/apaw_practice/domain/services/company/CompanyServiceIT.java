package es.upm.miw.apaw_practice.domain.services.company;

import es.upm.miw.apaw_practice.domain.models.company.Company;
import es.upm.miw.apaw_practice.domain.persistence_ports.company.CompanyPersistence;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class CompanyServiceIT {

    @Mock
    private CompanyPersistence companyPersistence;

    @InjectMocks
    private CompanyService companyService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindByCompanyname() {
        String companyName = "TechCorp";
        Company company = new Company(companyName, "New York", "Technology", null, null);
        when(companyPersistence.findByCompanyname(companyName)).thenReturn(company);

        Company result = companyService.findByCompanyname(companyName);

        assertNotNull(result);
        assertEquals(companyName, result.getCompanyname());
        assertEquals("New York", result.getLocation());
        verify(companyPersistence, times(1)).findByCompanyname(companyName);
    }

    @Test
    void testUpdateIndustry() {
        String companyName = "TechCorp";
        String newIndustry = "Renewable Energy";

        doNothing().when(companyPersistence).updateIndustry(companyName, newIndustry);

        companyService.updateIndustry(companyName, newIndustry);

        verify(companyPersistence, times(1)).updateIndustry(companyName, newIndustry);
    }
}
