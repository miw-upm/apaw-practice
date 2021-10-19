package es.upm.miw.apaw_practice.adapters.mongodb.department.persistence;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.models.department.Company;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

@TestConfig
public class CompanyMongodbIT {

    @Autowired
    private CompanyPersistenceMongodb companyPersistenceMongodb;

    @Test
    void testReadNotFound() {
        assertThrows(NotFoundException.class, () -> this.companyPersistenceMongodb.read("0"));
    }

    @Test
    void testCifNotExist() {
        assertFalse(this.companyPersistenceMongodb.existCif("0"));
    }

    @Test
    void testCifExist() {
        assertTrue(this.companyPersistenceMongodb.existCif("A12312345"));
    }

    @Test
    void testReadAndUpdate(){
        String cif = "C32132154";
        String newDireccion = "new direccion";

        Company company = this.companyPersistenceMongodb.read(cif);
        company.setDirection(newDireccion);
        this.companyPersistenceMongodb.update(cif, company);

        Company companyDb = this.companyPersistenceMongodb.read(cif);
        assertEquals(companyDb.getDirection(), newDireccion);
    }
}
