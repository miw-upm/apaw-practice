package es.upm.miw.apaw_practice.domain.models.movies;

import es.upm.miw.apaw_practice.TestConfig;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestConfig
class AwardTest {

    @Test
    void testBuilder() {
        Award award = Award.builder()
                .nameCategoryAndYear("Best Picture 2023")
                .name("Oscar")
                .category("Best Picture")
                .year(LocalDate.of(2023, 3, 27))
                .build();

        assertEquals("Best Picture 2023", award.getNameCategoryYear());
        assertEquals("Oscar", award.getName());
        assertEquals("Best Picture", award.getCategory());
        assertEquals(LocalDate.of(2023, 3, 27), award.getYear());
    }
}
