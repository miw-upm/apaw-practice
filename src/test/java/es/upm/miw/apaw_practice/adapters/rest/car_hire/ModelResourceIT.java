package es.upm.miw.apaw_practice.adapters.rest.car_hire;

import es.upm.miw.apaw_practice.adapters.rest.RestTestConfig;
import es.upm.miw.apaw_practice.domain.models.car_hire.Model;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@RestTestConfig
class ModelResourceIT {

    @Autowired
    private WebTestClient webTestClient;

    //ToDo: testFindModelByVehicleVinNumberBadRequest()

    @Test
    void testFindModelByVehicleVinNumber() {
        this.webTestClient
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path(ModelResource.MODELS + ModelResource.SEARCH)
                        .queryParam("q", "VIN_Number:WVGZZZ5NZJM131395")
                        .build())
                .exchange()
                .expectStatus().isOk()
                .expectBody(Model.class)
                .value(model -> assertEquals("Opel Insignia", model.getType()))
                .value(model -> assertEquals("Tipo Berlina, automático", model.getDescription()))
                .value(model -> assertEquals(130, model.getEnginePower()))
                .value(model -> assertEquals(new BigDecimal("50"), model.getVehicleList().get(0).getDailyCost()))
                .value(model -> assertEquals(32000, model.getVehicleList().get(0).getKilometersAmount()))
                .value(model -> assertTrue(model.getVehicleList().get(0).getGoodCondition()));
    }
}
