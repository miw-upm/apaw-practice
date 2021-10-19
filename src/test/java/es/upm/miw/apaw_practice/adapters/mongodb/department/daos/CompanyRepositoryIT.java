package es.upm.miw.apaw_practice.adapters.mongodb.department.daos;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.adapters.mongodb.department.entities.CompanyEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestConfig
public class CompanyRepositoryIT {

    @Autowired
    private CompanyRepository companyRepository;

    @Test
    void testFindByCif() {
        assertTrue(this.companyRepository.findByCif("A12312345").isPresent());
        CompanyEntity companyEntity = this.companyRepository
                .findByCif("A12312345")
                .get();
        assertEquals("Calle del Dr Cortezo, 6, 28012 Madrid", companyEntity.getDirection());
    }
}
