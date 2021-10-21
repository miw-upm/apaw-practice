package es.upm.miw.apaw_practice.adapters.rest.Class;

import es.upm.miw.apaw_practice.adapters.rest.RestTestConfig;
import es.upm.miw.apaw_practice.domain.models.Class.Learner;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

@RestTestConfig
public class LearnerRecourceIT {
    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private LearnerResource learnerResource;

    @Test
    void testUpdate(){
        Learner learner = new Learner("wang",25,false);

        this.webTestClient
                .put()
                .uri(LearnerResource.theLearner + "/wang" )
                .body(BodyInserters.fromValue(learner))
                .exchange()
                .expectStatus()
                .isOk();
    }
}
