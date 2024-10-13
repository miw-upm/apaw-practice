package es.upm.miw.apaw_practice.domain.services.theme_park;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.adapters.mongodb.theme_park.ThemeParkSeederService;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.models.theme_park.Operator;
import es.upm.miw.apaw_practice.domain.persistence_ports.theme_park.OperatorPersistence;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestConfig
class OperatorServiceIT {

    @Autowired
    private OperatorService operatorService;

    @Autowired
    private OperatorPersistence operatorPersistence;

    @Autowired
    private ThemeParkSeederService themeParkSeederService;

    @Test
    void testDelete() {
        List<Operator> operators = operatorPersistence.readAll().toList();
        assertNotNull(operators.get(0));
        String idEmployee = operators.get(0).getIdEmployee();
        this.operatorService.delete(idEmployee);
        assertThrows(NotFoundException.class, () ->
                operatorPersistence.readByIdEmployee(idEmployee));

        this.themeParkSeederService.deleteAll();
        this.themeParkSeederService.seedDatabase();
    }
}
