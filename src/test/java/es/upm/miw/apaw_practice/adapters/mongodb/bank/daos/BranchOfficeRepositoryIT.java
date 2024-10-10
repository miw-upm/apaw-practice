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
        assertTrue(this.branchOfficeRepository.findByBuildingName("Building4").isPresent());
        BranchOfficeEntity branchOffice = this.branchOfficeRepository.findByBuildingName("Building4").get();
        assertEquals("Building4", branchOffice.getBuildingName());
        assertEquals(20, branchOffice.getEmployees());
        assertEquals(1, branchOffice.getAtmNumber());
        assertNotNull(branchOffice.getClients());
    }

}
