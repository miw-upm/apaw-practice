package es.upm.miw.apaw_practice.adapters.mongodb.videogame.persistence;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.adapters.mongodb.videogame.VideoGameSeederService;
import es.upm.miw.apaw_practice.domain.models.videogame.ConsoleCompany;
import es.upm.miw.apaw_practice.domain.persistence_ports.videogame.ConsoleCompanyPersistence;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestConfig
class ConsoleCompanyPersistenceMongodbIT {

    @Autowired
    private ConsoleCompanyPersistence consoleCompanyPersistence;

    @Autowired
    private VideoGameSeederService videoGameSeederService;

    @Test
    void testUpdateConsoleCompany() {
        Integer number = Integer.valueOf(4000);
        Optional<ConsoleCompany> consoleCompany = this.consoleCompanyPersistence.readAll()
                .filter(consoleCompany1 -> number.compareTo(consoleCompany1.getNumberOfEmployee()) == 0)
                .findFirst();
        assertTrue(consoleCompany.isPresent());
        assertTrue(consoleCompany.get().isActive());

        consoleCompany.get().setActive(false);

        this.consoleCompanyPersistence.updateConsoleCompany(consoleCompany.get());

        Optional<ConsoleCompany> newConsoleCompany = this.consoleCompanyPersistence.readAll()
                .filter(consoleCompany1 -> number.compareTo(consoleCompany1.getNumberOfEmployee())==0)
                .findFirst();
        assertTrue(newConsoleCompany.isPresent());
        assertFalse(newConsoleCompany.get().isActive());

        videoGameSeederService.deleteAll();
        videoGameSeederService.seedDatabase();
    }
}
