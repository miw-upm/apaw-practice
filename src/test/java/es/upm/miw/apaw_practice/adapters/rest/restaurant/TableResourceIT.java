package es.upm.miw.apaw_practice.adapters.rest.restaurant;

import es.upm.miw.apaw_practice.adapters.rest.RestTestConfig;
import es.upm.miw.apaw_practice.domain.models.restaurant.Reserve;
import es.upm.miw.apaw_practice.domain.models.restaurant.Table;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static es.upm.miw.apaw_practice.adapters.rest.restaurant.TableResource.*;


@RestTestConfig
class TableResourceIT {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void testReadHoldersByNumber(){
        this.webTestClient
                .get()
                .uri(TABLES+"/1"+RESERVES+HOLDER)
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Reserve.class)
                .value(reserves -> assertEquals("Jose",reserves.get(0).getHolder()))
                .value(reserves -> assertEquals("Paco",reserves.get(1).getHolder()));
    }

    @Test
    void testUpdate(){
        List<Reserve> reserves = Arrays.asList(
                new Reserve(LocalDate.now().plusDays(1), 1,"Jesus"),
                new Reserve(LocalDate.now().plusDays(1), 1,"Lucia")
        );
        this.webTestClient
                .put()
                .uri(TABLES+ID+RESERVES,"3")
                .body(BodyInserters.fromValue(reserves))
                .exchange()
                .expectStatus().isOk()
                .expectBody(Table.class)
                .value(table -> assertTrue(table.getReserves().size() == 2));
    }

    @Test
    void testUpdateStyle(){
        this.webTestClient
                .patch()
                .uri(TABLES)
                .body(BodyInserters.fromValue("classic"))
                .exchange()
                .expectStatus().isOk();
    }
}
