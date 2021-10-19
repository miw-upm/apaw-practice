package es.upm.miw.apaw_practice.adapters.mongodb.department.daos;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.adapters.mongodb.department.entities.ManagerEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestConfig
public class ManagerRepositoryIT {

    @Autowired
    private  ManagerRepository managerRepository;

    @Test
    void testFindByCif() {
        assertTrue(this.managerRepository.findByEmail("d.jhon@company1.com").isPresent());
        ManagerEntity managerEntity = this.managerRepository
                .findByEmail("d.jhon@company1.com")
                .get();
        assertEquals("123123123", managerEntity.getPhoneNumber());
    }
}
