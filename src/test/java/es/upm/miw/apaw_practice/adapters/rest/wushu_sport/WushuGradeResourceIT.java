package es.upm.miw.apaw_practice.adapters.rest.wushu_sport;

import es.upm.miw.apaw_practice.adapters.rest.RestTestConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;

import static es.upm.miw.apaw_practice.adapters.rest.wushu_sport.WushuGradeResource.WUSHU_GRADES;
import static es.upm.miw.apaw_practice.adapters.rest.wushu_sport.WushuGradeResource.GRADE_TITLE_ID;

@RestTestConfig
public class WushuGradeResourceIT {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void testDelete() {
        this.webTestClient
                .delete()
                .uri(WUSHU_GRADES + GRADE_TITLE_ID, "kk")
                .exchange()
                .expectStatus().isOk();
    }
}