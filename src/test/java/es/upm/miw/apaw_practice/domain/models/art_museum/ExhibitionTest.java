package es.upm.miw.apaw_practice.domain.models.art_museum;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ExhibitionTest {
    @Test
    void testBuilderFull() {
        Exhibition exhibition = Exhibition.builder()
                .name("Spanish authors")
                .dateOfExhibition(LocalDateTime.of(2024, 10, 10,10, 0))
                .price(BigDecimal.valueOf(20.00))
                .build();
        assertEquals("Spanish authors", exhibition.getName());
        assertEquals(LocalDateTime.of(2024, 10, 10,10, 0), exhibition.getDateOfExhibition());
        assertEquals(BigDecimal.valueOf(20.00), exhibition.getPrice());
    }

    @Test
    void testBuilderPartial() {
        Exhibition exhibition = Exhibition.builder()
                .name("Mixed arts")
                .dateOfExhibition(LocalDateTime.of(2025, 10, 10,10, 0))
                .build();
        assertEquals("Mixed arts", exhibition.getName());
        assertEquals(LocalDateTime.of(2025, 10, 10,10, 0), exhibition.getDateOfExhibition());
    }
}
