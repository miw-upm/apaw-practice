package es.upm.miw.apaw_practice.adapters.rest.wushu_sport;

import es.upm.miw.apaw_practice.adapters.rest.RestTestConfig;
import es.upm.miw.apaw_practice.domain.models.wuhshu_sport.WushuGrade;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import java.time.LocalDate;
import java.util.List;

import static es.upm.miw.apaw_practice.adapters.rest.wushu_sport.WushuGradeResource.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@RestTestConfig
class WushuGradeResourceIT {

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
        WushuGrade wushuGrade= new WushuGrade(LocalDate.now().minusYears(1), "Diamond Shi", 3);
        this.webTestClient
                .patch()
                .uri(WUSHU_GRADES + GRADE_TITLE_ID, "Diamond Shi")
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
    @Test
    void testGetGradeTitleListByCategory(){
        this.webTestClient
                .get()
                .uri(WUSHU_GRADES + CATEGORY_ID + GRADE_TITLE_LIST, "changquan")
                .exchange()
                .expectStatus().isOk()
                .expectBody(List.class)
                .value(Assertions::assertNotNull)
                .value(listData -> assertEquals(2, listData.size()));
    }
}