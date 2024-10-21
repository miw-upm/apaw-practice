package es.upm.miw.apaw_practice.domain.services.company;

import es.upm.miw.apaw_practice.domain.models.company.Company;
import es.upm.miw.apaw_practice.domain.persistence_ports.company.CompanyPersistence;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

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
    @Test
    void testFindManagementNamesByIndustryAndDescription() {
        String industry = "Technology";
        String description = "Office Supplies";


        List<String> mockManagementNames = Arrays.asList("John Doe", "Jane Smith");
        when(companyPersistence.findManagementNamesByIndustryAndDescription(industry, description))
                .thenReturn(mockManagementNames);


        List<String> managementNames = companyService.findManagementNamesByIndustryAndDescription(industry, description);


        assertNotNull(managementNames);
        assertEquals(2, managementNames.size());
        assertEquals("John Doe", managementNames.get(0));
        assertEquals("Jane Smith", managementNames.get(1));


        verify(companyPersistence, times(1)).findManagementNamesByIndustryAndDescription(industry, description);
    }
}
