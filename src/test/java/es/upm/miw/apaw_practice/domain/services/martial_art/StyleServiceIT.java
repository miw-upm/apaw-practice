package es.upm.miw.apaw_practice.domain.services.martial_art;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;

import es.upm.miw.apaw_practice.domain.models.martial_art.Style;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
@TestConfig
public class StyleServiceIT {
    @Autowired
    private StyleService styleService;

    @Test
    void testCreateAndRead() {
        Style  style = new Style(
                "MMA",
                "Estilo  para UFC",
                100,
                "Norte america"
        );
        Style style1 = this.styleService.create(style);
        assertEquals("MMA", style1.getName());
        assertEquals("Estilo  para UFC", style1.getDescription());
        assertEquals(100, style1.getPopularity());
    }

    @Test
    void testRead() {
        Style style = this.styleService.read("Estilo de combate");
        assertEquals("Estilo de combate", style.getName());
    }

    @Test
    void testDelete() {
        Style  style = new Style(
                "Lucha libre",
                "Estilo  para show",
                1000,
                "Norte america"
        );
        this.styleService.create(style);
        this.styleService.delete("Lucha libre");
        assertThrows(NotFoundException.class, () -> this.styleService.read("Lucha libre"));
    }
}
