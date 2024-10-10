package es.upm.miw.apaw_practice.adapters.mongodb.bank.persistence;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.adapters.mongodb.bank.BankSeederService;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.models.bank.BranchOffice;
import es.upm.miw.apaw_practice.domain.models.bank.Client;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.List;

import static java.util.Collections.emptyList;
import static org.junit.jupiter.api.Assertions.*;

@TestConfig
class BranchOfficePersistenceMongodbIT {

    @Autowired
    private BranchOfficePersistenceMongodb branchOfficePersistenceMongodb;
    @Autowired
    private BankSeederService bankSeederService;

    @Test
    void testCreateNoClients() {
        BranchOffice branchOffice = new BranchOffice("Building1", 10, 10, emptyList());
        BranchOffice branchOfficeSaved = this.branchOfficePersistenceMongodb.create(branchOffice);
        assertNotNull(branchOfficeSaved);
        assertEquals("Building1", branchOfficeSaved.getBuildingName());
        assertEquals(10, branchOfficeSaved.getEmployees());
        assertEquals(10, branchOfficeSaved.getAtmNumber());
        assertNotNull(branchOfficeSaved.getClients());
    }

    @Test
    void testCreate() {
        bankSeederService.deleteAll();
        bankSeederService.seedDatabase();
        Client client = new Client("11111111A", "Client1", "Client1", 111111111, "email1@example.com", emptyList());
        BranchOffice branchOffice = new BranchOffice("Building1", 10, 10, List.of(client));
        BranchOffice branchOfficeSaved = this.branchOfficePersistenceMongodb.create(branchOffice);
        assertNotNull(branchOfficeSaved);
        assertEquals("Building1", branchOfficeSaved.getBuildingName());
        assertEquals(10, branchOfficeSaved.getEmployees());
        assertEquals(10, branchOfficeSaved.getAtmNumber());
        assertNotNull(branchOfficeSaved.getClients());
    }

    @Test
    void testGetAssociatedBalanceNoBranchOffice() {
        assertThrows(NotFoundException.class, () -> this.branchOfficePersistenceMongodb.getAssociatedBalance("A"));
    }

    @Test
    void testGetAssociatedBalance() {
        bankSeederService.deleteAll();
        bankSeederService.seedDatabase();
        assertEquals(new BigDecimal("40100.0"),this.branchOfficePersistenceMongodb.getAssociatedBalance("Building1"));
    }

}
