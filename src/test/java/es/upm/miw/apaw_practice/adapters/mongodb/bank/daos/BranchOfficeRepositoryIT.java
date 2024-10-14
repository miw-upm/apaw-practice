package es.upm.miw.apaw_practice.adapters.mongodb.bank.daos;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.adapters.mongodb.bank.entities.BranchOfficeEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

@TestConfig
class BranchOfficeRepositoryIT {

    @Autowired
    private BranchOfficeRepository branchOfficeRepository;

    @Test
    void testFindByBuildingName() {
        assertTrue(this.branchOfficeRepository.findByBuildingName("Building1").isPresent());
        BranchOfficeEntity branchOffice = this.branchOfficeRepository.findByBuildingName("Building1").get();
        assertEquals("Building1", branchOffice.getBuildingName());
        assertEquals(200, branchOffice.getEmployees());
        assertEquals(5, branchOffice.getAtmNumber());
        assertNotNull(branchOffice.getClients());
    }

}
