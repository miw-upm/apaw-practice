package es.upm.miw.apaw_practice.adapters.rest.delivery_food;

import es.upm.miw.apaw_practice.adapters.rest.RestTestConfig;
import es.upm.miw.apaw_practice.domain.models.delivery_food.MenuCategory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.List;

import static es.upm.miw.apaw_practice.adapters.rest.delivery_food.MenuCategoryResource.*;
import static org.junit.jupiter.api.Assertions.*;

@RestTestConfig
class MenuCategoryResourceIT {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void testFindNameByActive() {
        List<String> menuCategoriesSave = List.of("Salads", "Seafood", "Vegetarian", "Grilled");

        this.webTestClient
                .get()
                .uri(MENU_CATEGORIES + "/" + Boolean.TRUE + NAME)
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(MenuCategory.class)
                .value(Assertions::assertNotNull)
                .value(menuCategories -> assertTrue(
                        menuCategories.stream()
                                .map(MenuCategory::getName)
                                .allMatch(menuCategoriesSave::contains)));
    }
}