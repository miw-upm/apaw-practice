package es.upm.miw.apaw_practice.adapters.mongodb.shopping_center.daos;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.adapters.mongodb.shopping_center.entities.EmployeeShoppingCenterEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestConfig
class EmployeeShoppingCenterRepositoryIT {

    @Autowired
    private EmployeeShoppingCenterRepository employeeShoppingCenterRepository;

    @Test
    void testFindByDni() {
        assertTrue(this.employeeShoppingCenterRepository.findByDni("11122233E").isPresent());
        EmployeeShoppingCenterEntity employeeShoppingCenterEntity =
                this.employeeShoppingCenterRepository.findByDni("11122233E").get();
        assertEquals("Maria", employeeShoppingCenterEntity.getName());
        assertEquals("600000005", employeeShoppingCenterEntity.getPhone());
    }
}
