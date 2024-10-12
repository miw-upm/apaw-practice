package es.upm.miw.apaw_practice.adapters.rest.wushu_sport;

import es.upm.miw.apaw_practice.adapters.rest.RestTestConfig;
import es.upm.miw.apaw_practice.domain.models.wuhshu_sport.WushuGrade;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import java.time.LocalDate;

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
    @Test
    void testUpdate() {
        WushuGrade wushuGrade= new WushuGrade(LocalDate.now().minusYears(5), "Jin Shi", 3);
        this.webTestClient
                .patch()
                .uri(WUSHU_GRADES + GRADE_TITLE_ID, "Jin Shi")
                .body(BodyInserters.fromValue(wushuGrade.getGradeLevel()))
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    void testUpdateNotFound() {
        this.webTestClient
                .patch()
                .uri(WUSHU_GRADES + GRADE_TITLE_ID, "kk")
                .body(BodyInserters.fromValue(3))
                .exchange()
                .expectStatus().isNotFound();
    }
}