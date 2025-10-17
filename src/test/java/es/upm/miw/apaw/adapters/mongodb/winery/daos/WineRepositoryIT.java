package es.upm.miw.apaw.adapters.mongodb.winery.daos;

import es.upm.miw.apaw.adapters.mongodb.winery.entities.WineEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@ActiveProfiles("test")
public class WineRepositoryIT {

    @Autowired
    private WineRepository wineRepository;
    @Autowired
    private TastingSessionRepository tastingSessionRepository;

    @Autowired
    private WinerySeeder winerySeeder;

    @BeforeEach
    void resetDb() {
        winerySeeder.deleteAll();
        winerySeeder.seedDatabase();
    }

    @Test
    void testRead() {
        assertTrue(this.wineRepository.findById(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0001")).isPresent());
        WineEntity wine = this.wineRepository.findById(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0001")).get();
        assertThat(wine.getId()).isEqualTo(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0001"));
        assertThat(wine.getPrice()).isEqualByComparingTo("25.50");
        assertThat(wine.getYear()).isEqualTo(2020);
        assertThat(wine.getName()).isEqualTo("Cabernet Sauvignon");
        assertThat(wine.getAlcoholPercentage()).isEqualTo(13.5);
    }


    @Test
    void testDelete() {
        UUID wineId = UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0003");

        assertTrue(this.wineRepository.findById(wineId).isPresent());
        this.wineRepository.deleteById(wineId);

        assertFalse(this.wineRepository.findById(wineId).isPresent());
    }

    @Test
    void testSumPricesByComment() {
        String comment = "Great organization and excellent wine selection";

        BigDecimal sum = this.tastingSessionRepository.findAll().stream()
                .filter(session -> session.getEvaluationEntities().stream()
                        .anyMatch(e -> e.getComment().equalsIgnoreCase(comment)))
                .flatMap(session -> session.getWineEntities().stream())
                .map(WineEntity::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        assertThat(sum).isGreaterThan(BigDecimal.ZERO);
        assertThat(sum).isEqualByComparingTo("44.40");
    }

}
