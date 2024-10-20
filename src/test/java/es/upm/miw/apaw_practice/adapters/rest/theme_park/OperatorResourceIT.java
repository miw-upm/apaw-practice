package es.upm.miw.apaw_practice.adapters.rest.theme_park;

import es.upm.miw.apaw_practice.adapters.mongodb.theme_park.ThemeParkSeederService;
import es.upm.miw.apaw_practice.adapters.rest.RestTestConfig;

import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;

import es.upm.miw.apaw_practice.domain.persistence_ports.theme_park.OperatorPersistence;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;


import static org.junit.jupiter.api.Assertions.*;

@RestTestConfig
class OperatorResourceIT {

    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private OperatorPersistence operatorPersistence;

    @Autowired
    private ThemeParkSeederService themeParkSeederService;

    @Test
    void testDeleteExistingOperator() {
        assertNotNull(operatorPersistence.readByIdEmployee("14123H"));
        this.webTestClient
                .delete()
                .uri(OperatorResource.OPERATORS + OperatorResource.NAME_ID, "14123H")
                .exchange()
                .expectStatus()
                .isOk();

        assertThrows(NotFoundException.class, () ->
                operatorPersistence.readByIdEmployee("14123H"));

        themeParkSeederService.deleteAll();
        themeParkSeederService.seedDatabase();
    }
}
