package es.upm.miw.apaw_practice.domain.services.music_festival;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.domain.models.music_festival.MusicFestivalBudgetUpdating;
import es.upm.miw.apaw_practice.domain.persistence_ports.music_festival.MusicFestivalPersistence;
import java.math.BigDecimal;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@TestConfig
class MusicFestivalServiceIT {

    @Autowired
    private MusicFestivalService musicFestivalService;

    @Autowired
    private MusicFestivalPersistence musicFestivalPersistence;

    @Test
    void testUpdateBudgets() {
        List<MusicFestivalBudgetUpdating> updatingList = List.of(
                new MusicFestivalBudgetUpdating("SpringFest", BigDecimal.valueOf(210000)),
                new MusicFestivalBudgetUpdating("AutumnRock", BigDecimal.valueOf(160000))
        );
        this.musicFestivalService.updateBudgets(updatingList.stream());
        assertTrue(updatingList.get(0).toString().contains("SpringFest"));
        assertEquals(BigDecimal.valueOf(210000), this.musicFestivalPersistence.readByName("SpringFest").getBudget());
        assertEquals(BigDecimal.valueOf(160000), this.musicFestivalPersistence.readByName("AutumnRock").getBudget());
        updatingList = List.of(
                new MusicFestivalBudgetUpdating("SpringFest", BigDecimal.valueOf(200000)),
                new MusicFestivalBudgetUpdating("AutumnRock", BigDecimal.valueOf(150000))
        );
        this.musicFestivalService.updateBudgets(updatingList.stream());
    }
}