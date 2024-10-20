package es.upm.miw.apaw_practice.adapters.rest.videogame;

import es.upm.miw.apaw_practice.adapters.mongodb.videogame.VideoGameSeederService;
import es.upm.miw.apaw_practice.adapters.rest.RestTestConfig;
import es.upm.miw.apaw_practice.domain.models.videogame.ConsoleCompany;
import es.upm.miw.apaw_practice.domain.models.videogame.ConsoleCompanyActivedUpdating;
import es.upm.miw.apaw_practice.domain.persistence_ports.videogame.ConsoleCompanyPersistence;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@RestTestConfig
public class ConsoleCompanyResourceIT {
    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private ConsoleCompanyPersistence consoleCompanyPersistence;

    @Autowired
    private VideoGameSeederService videoGameSeederService;

    @Test
    void testUpdateNotFound(){
        this.webTestClient.patch()
                .uri(ConsoleCompanyResource.CONSOLE_COMPANIES + ConsoleCompanyResource.COMPANY_INFORMATION,"UPM")
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    void testUpdateActived(){
        List<ConsoleCompany> consoleCompanies = this.consoleCompanyPersistence.readAll()
                .filter(consoleCompany -> !consoleCompany.isActive()).toList();
        Stream<ConsoleCompanyActivedUpdating> consoleCompanyActivedUpdatingList = consoleCompanies.stream()
                .map(consoleCompaniesClosed -> new ConsoleCompanyActivedUpdating(consoleCompaniesClosed.getCompanyInformation(), consoleCompaniesClosed.isActive()));

        this.webTestClient.patch()
                .uri(ConsoleCompanyResource.CONSOLE_COMPANIES)
                .body(BodyInserters.fromValue(consoleCompanyActivedUpdatingList))
                .exchange()
                .expectStatus().isOk();

        List<ConsoleCompany> closedConsoleCompanies = this.consoleCompanyPersistence
                .readAll()
                .filter(consoleCompany -> !consoleCompany.isActive()).toList();

        assertEquals(0, closedConsoleCompanies.size());
        assertTrue(this.consoleCompanyPersistence.readAll().allMatch(ConsoleCompany::isActive));

        videoGameSeederService.deleteAll();
        videoGameSeederService.seedDatabase();
    }
}