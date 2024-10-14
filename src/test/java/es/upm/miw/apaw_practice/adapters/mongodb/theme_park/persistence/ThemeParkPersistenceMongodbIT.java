package es.upm.miw.apaw_practice.adapters.mongodb.theme_park.persistence;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.adapters.mongodb.theme_park.ThemeParkSeederService;
import es.upm.miw.apaw_practice.domain.models.theme_park.ThemePark;
import es.upm.miw.apaw_practice.domain.persistence_ports.theme_park.ThemeParkPersistence;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@TestConfig
class ThemeParkPersistenceMongodbIT {

    @Autowired
    private ThemeParkPersistence themeParkPersistence;

    @Autowired
    private ThemeParkSeederService themeParkSeederService;

    @Test
    void testUpdateThemePark() {
        BigDecimal bd1 = new BigDecimal("60.00");
        Optional<ThemePark> themePark = this.themeParkPersistence.readAll()
                .filter(themeP -> bd1.compareTo(themeP.getPrice()) == 0)
                .findFirst();
        assertTrue(themePark.isPresent());
        assertTrue(themePark.get().getOpened());

        themePark.get().setOpened(false);

        this.themeParkPersistence.updateThemePark(themePark.get());

        Optional<ThemePark> newThemePark = this.themeParkPersistence.readAll()
                .filter(themeP -> bd1.compareTo(themeP.getPrice()) == 0)
                .findFirst();
        assertTrue(newThemePark.isPresent());
        assertFalse(newThemePark.get().getOpened());

        themeParkSeederService.deleteAll();
        themeParkSeederService.seedDatabase();

    }

}