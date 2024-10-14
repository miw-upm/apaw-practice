package es.upm.miw.apaw_practice.domain.services.theme_park;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.adapters.mongodb.theme_park.ThemeParkSeederService;
import es.upm.miw.apaw_practice.domain.models.theme_park.ThemePark;
import es.upm.miw.apaw_practice.domain.persistence_ports.theme_park.ThemeParkPersistence;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestConfig
class ThemeParkServiceIT {

    @Autowired
    private ThemeParkService themeParkService;

    @Autowired
    private ThemeParkPersistence themeParkPersistence;

    @Autowired
    private ThemeParkSeederService themeParkSeederService;

    @Test
    void testUpdate() {
        List<ThemePark> themeParks =
                themeParkPersistence.readAll()
                                    .filter(themePark -> !themePark.getOpened()).toList();
        assertNotNull(themeParks.get(0));
        assertFalse(themeParks.get(0).getOpened());

        String idPark = themeParks.get(0).getId();
        this.themeParkService.updateParkStatus(idPark);

        ThemePark newThemePark = themeParkPersistence.readById(themeParks.get(0).getId());
        assertTrue(newThemePark.getOpened());

        this.themeParkSeederService.deleteAll();
        this.themeParkSeederService.seedDatabase();
    }
}