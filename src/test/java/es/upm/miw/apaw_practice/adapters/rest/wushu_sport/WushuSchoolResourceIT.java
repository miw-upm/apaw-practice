package es.upm.miw.apaw_practice.adapters.rest.wushu_sport;

import es.upm.miw.apaw_practice.adapters.rest.RestTestConfig;
import es.upm.miw.apaw_practice.domain.models.wuhshu_sport.CompetitionForm;
import es.upm.miw.apaw_practice.domain.models.wuhshu_sport.Competitor;
import es.upm.miw.apaw_practice.domain.models.wuhshu_sport.WushuGrade;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestTestConfig
public class WushuSchoolResourceIT {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void testUpdate() {
        WushuGrade wushuGrade= new WushuGrade(LocalDate.now().minusYears(5), "Jin Shi", 1);
        List<CompetitionForm> competitionForms = new ArrayList<>();
        competitionForms.add(new CompetitionForm(9.45, Duration.ofMinutes(1).plusSeconds(30), "changquan"));

        Competitor competitor1 = new Competitor("WU/A/00131", 1, LocalDate.now(), wushuGrade, List.of(competitionForms.get(0)));
        Competitor competitor2 = new Competitor("WU/A/00138", 1, LocalDate.now(), wushuGrade, List.of(competitionForms.get(0)));
        List<Competitor> competitorsList = Arrays.asList( competitor1,competitor2);
        this.webTestClient.put()
                .uri(WushuSchoolResource.WUSHU_SCHOOL + WushuSchoolResource.NAME_ID + WushuSchoolResource.COMPETITORS_ITEMS, "kk")
                .body(BodyInserters.fromValue(competitorsList))
                .exchange()
                .expectStatus().isNotFound();
    }
}
