package es.upm.miw.apaw_practice.adapters.mongodb.computer_store.daos;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.adapters.mongodb.computer_store.entities.RepairEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


@TestConfig
class RepairRepositoryIT {
    private static final String REPAIR_NUMBER = "1234";
    @Autowired
    private RepairRepository repairRepository;

    @Test
    void testFindByRepairNumber() {
        assertTrue(this.repairRepository.findByRepairNumber(REPAIR_NUMBER).isPresent());
        RepairEntity repair = this.repairRepository.findByRepairNumber(REPAIR_NUMBER).get();
        assertTrue(repair.getBeginTime().isBefore(LocalDateTime.now()));
        assertTrue(repair.getEndTime().isAfter(LocalDateTime.now().plusDays(1)));
        assertEquals("MSI Pro gaming PC", repair.getComputerEntity().getName());
    }
}
