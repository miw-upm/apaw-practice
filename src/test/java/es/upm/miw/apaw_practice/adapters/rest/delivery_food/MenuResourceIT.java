package es.upm.miw.apaw_practice.adapters.rest.delivery_food;

import es.upm.miw.apaw_practice.adapters.rest.RestTestConfig;
import es.upm.miw.apaw_practice.domain.models.delivery_food.Menu;
import es.upm.miw.apaw_practice.domain.models.delivery_food.MenuCategory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RestTestConfig
class MenuResourceIT {

    @Autowired
    private WebTestClient webTestClient;
    @Test
    void testDelete() {
        this.webTestClient
                .delete()
                .uri(MenuResource.MENU + MenuResource.NAME_ID, "Dessert Extravaganza")
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    void testUpdate() {
        String menuNameUpdate = "Vegetarian Delight";
        MenuCategory menuCategory = new MenuCategory();
        menuCategory.setName("Grilled");
        menuCategory.setDescription("Description");
        menuCategory.setActive(Boolean.TRUE);
        Menu menu = new Menu(menuNameUpdate, "Update description", List.of(menuCategory), 9.8);
        this.webTestClient
                .put()
                .uri(MenuResource.MENU + MenuResource.NAME_ID, menuNameUpdate)
                .body(BodyInserters.fromValue(menu))
                .exchange()
                .expectStatus().isOk()
                .expectBody(Menu.class)
                .value(menuResponse -> {
                    assertEquals(menu.getName(), menuResponse.getName());
                    assertEquals(menu.getDescription(), menuResponse.getDescription());
                    assertEquals(menu.getRating(), menuResponse.getRating());
                    assertEquals(1, menuResponse.getCategories().size());
                });
    }
}
