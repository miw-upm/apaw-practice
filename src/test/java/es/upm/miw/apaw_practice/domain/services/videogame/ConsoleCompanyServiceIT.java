package es.upm.miw.apaw_practice.domain.services.videogame;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.adapters.mongodb.videogame.VideoGameSeederService;
import es.upm.miw.apaw_practice.domain.models.videogame.ConsoleCompany;
import es.upm.miw.apaw_practice.domain.persistence_ports.videogame.ConsoleCompanyPersistence;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestConfig
public class ConsoleCompanyServiceIT {

    @Autowired
    private ConsoleCompanyService consoleCompanyService;

    @Autowired
    private ConsoleCompanyPersistence consoleCompanyPersistence;

    @Autowired
    private VideoGameSeederService videoGameSeederService;

    @Test
    void testUpdate(){
        List<ConsoleCompany> consoleCompanies =
                consoleCompanyPersistence.readAll()
                .filter(consoleCompany -> !consoleCompany.isActive()).toList();
        assertNotNull(consoleCompanies.get(0));
        assertFalse(consoleCompanies.get(0).isActive());

        String nameConsoleInformation = consoleCompanies.get(0).getCompanyInformation();
        this.consoleCompanyService.updateActiveCompany(nameConsoleInformation);

        ConsoleCompany newConsoleCompany = consoleCompanyPersistence.readById(consoleCompanies.get(0).getCompanyInformation());
        assertTrue(newConsoleCompany.isActive());

        this.videoGameSeederService.deleteAll();
        this.videoGameSeederService.seedDatabase();
    }
}