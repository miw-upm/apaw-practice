package es.upm.miw.apaw_practice.adapters.rest.theme_park;

import es.upm.miw.apaw_practice.adapters.mongodb.theme_park.ThemeParkSeederService;
import es.upm.miw.apaw_practice.adapters.rest.RestTestConfig;
import es.upm.miw.apaw_practice.domain.models.theme_park.ThemePark;
import es.upm.miw.apaw_practice.domain.models.theme_park.ThemeParkOpenedUpdating;
import es.upm.miw.apaw_practice.domain.persistence_ports.theme_park.ThemeParkPersistence;
import es.upm.miw.apaw_practice.domain.services.theme_park.ThemeParkService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@RestTestConfig
public class ThemeParkResourceIT {

    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private ThemeParkPersistence themeParkPersistence;

    @Autowired
    private ThemeParkService themeParkService;

    @Autowired
    private ThemeParkSeederService themeParkSeederService;

    @Test
    void testUpdateNotFound() {
        this.webTestClient
                .patch()
                .uri(ThemeParkResource.THEME_PARKS + ThemeParkResource.ID_ID, "kk")
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    void testUpdateOpened() {
        List<ThemePark> themeParks = this.themeParkPersistence
                                    .readAll()
                                    .filter(themePark -> !themePark.getOpened()).toList();

        Stream<ThemeParkOpenedUpdating> themeParkOpenedUpdatingList = themeParks.stream()
                .map(
                    themeParksClosed -> new ThemeParkOpenedUpdating(themeParksClosed.getId(), themeParksClosed.getOpened())
                );

        this.webTestClient
                .patch()
                .uri(ThemeParkResource.THEME_PARKS)
                .body(BodyInserters.fromValue(themeParkOpenedUpdatingList))
                .exchange()
                .expectStatus().isOk();

        List<ThemePark> closedThemeParks = this.themeParkPersistence
                .readAll()
                .filter(themePark -> !themePark.getOpened()).toList();

        assertEquals(0, closedThemeParks.size());
        assertTrue(this.themeParkPersistence
                                            .readAll()
                                            .allMatch(ThemePark::getOpened));

        themeParkSeederService.deleteAll();
        themeParkSeederService.seedDatabase();
    }
}
