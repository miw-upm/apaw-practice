package es.upm.miw.apaw_practice.adapters.rest.pharmacy;

import es.upm.miw.apaw_practice.adapters.mongodb.pharmacy.entities.ActiveIngredientEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.pharmacy.entities.DrugEntity;
import es.upm.miw.apaw_practice.adapters.rest.RestTestConfig;
import es.upm.miw.apaw_practice.domain.models.pharmacy.ActiveIngredient;
import es.upm.miw.apaw_practice.domain.models.pharmacy.Dispensing;
import es.upm.miw.apaw_practice.domain.models.pharmacy.Drug;
import es.upm.miw.apaw_practice.domain.models.shop.ShoppingCart;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RestTestConfig
public class DispensingResourceIT {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void testUpdateDispensingNotFound() {
        this.webTestClient
                .put()
                .uri(DispensingResource.DISPENSINGS)
                .body(BodyInserters.fromValue(new Dispensing()))
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    void testUpdateDispensing() {
        Drug drug =  new Drug("A9005", "Pirexin", false, new BigDecimal("5.15"));
        ActiveIngredient activeIngredient =  new ActiveIngredient("B9006", drug, List.of("100% Ibuprofeno"), 600);
        this.webTestClient
                .put()
                .uri(DispensingResource.DISPENSINGS)
                .body(BodyInserters.fromValue(new Dispensing("1", List.of(activeIngredient), LocalDateTime.of(2021, 7, 2, 21, 34))))
                .exchange()
                .expectStatus().isOk()
                .expectBody(Dispensing.class)
                .value(Assertions::assertNotNull)
                .value(dispensing -> {
                    assertEquals("1", dispensing.getId());
                    assertEquals(LocalDateTime.of(2021, 7, 2, 21, 34), dispensing.getDispensingTimestamp());
                    assertEquals(700, dispensing.getActiveIngredients().get(0).getDose());
                    assertEquals("100% Ibuprofeno", dispensing.getActiveIngredients().get(0).getComponents().get(0));
                    assertEquals(false, dispensing.getActiveIngredients().get(0).getDrug().getCommercialized());
                    assertEquals("Pirexin", dispensing.getActiveIngredients().get(0).getDrug().getName());
                    assertEquals(0,new BigDecimal("5.15").compareTo(dispensing.getActiveIngredients().get(0).getDrug().getPrice()));
                });

    }

    @Test
    void testDeleteDispensing() {
        this.webTestClient
                .delete()
                .uri(DispensingResource.DISPENSINGS + DispensingResource.ID_ID, "2")
                .exchange()
                .expectStatus().isOk();
    }
}
