package es.upm.miw.apaw_practice.domain.models.wushu_sport;

import es.upm.miw.apaw_practice.domain.models.wuhshu_sport.CompetitionForm;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CompetitionFormTest {

    @Test
    void testCompetitionFormBuilder() {
        CompetitionForm competitionForm = CompetitionForm.builder()
                .score(2.00)
                .duration(Duration.ofMinutes(1))
                .category("Changquan")
                .build();
        assertEquals(2.00, competitionForm.getScore());
        assertEquals(Duration.ofMinutes(1), competitionForm.getDuration());
        assertEquals("Changquan", competitionForm.getCategory());
    }

}
