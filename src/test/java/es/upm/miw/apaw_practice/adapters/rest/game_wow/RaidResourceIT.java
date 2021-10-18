package es.upm.miw.apaw_practice.adapters.rest.game_wow;

import es.upm.miw.apaw_practice.adapters.rest.RestTestConfig;
import es.upm.miw.apaw_practice.domain.models.game_wow.Boss;
import es.upm.miw.apaw_practice.domain.models.game_wow.Drop;
import es.upm.miw.apaw_practice.domain.models.game_wow.Feature;
import es.upm.miw.apaw_practice.domain.models.game_wow.Raid;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import java.util.Date;
import java.util.List;

@RestTestConfig
public class RaidResourceIT {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void testUpdate() {
        Date raidDate = new Date();
        Feature feature = new Feature("Legs", 171, 200, 100, "Use: Restores 1625 mana");
        Drop drop = new Drop("Plaguebringer's Stained Pants", "mage,priest,warlock", 264, feature);
        Boss boss = new Boss("Festergut", "25N", List.of(drop));
        Raid raidCreation = new Raid(raidDate, "ICC", "25N", 25, false, List.of(boss));

        this.webTestClient
                .put()
                .uri(RaidResource.GAMEWOW_RAIDS + RaidResource.ID_ID + RaidResource.DIFICULTY, "asd")
                .body(BodyInserters.fromValue("10H"))
                .exchange()
                .expectStatus().isNotFound();
    }
}
