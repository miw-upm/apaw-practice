package es.upm.miw.apaw_practice.domain.services.music_manager;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.domain.models.music_manager.BandActiveUpdating;
import es.upm.miw.apaw_practice.domain.persistence_ports.music_manager.BandPersistence;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestConfig
class BandServiceIT {

    @Autowired
    private BandService bandService;

    @Autowired
    private BandPersistence bandPersistence;

    @Test
    void testUpdateActives() {
        List<BandActiveUpdating> bandActiveUpdatingList = List.of(
                new BandActiveUpdating("Foo Fighters", false)
        );
        this.bandService.updateActives(bandActiveUpdatingList.stream());
        assertFalse(this.bandPersistence.read("Foo Fighters").getActive());
        bandActiveUpdatingList = List.of(
                new BandActiveUpdating("Foo Fighters", true)
        );
        this.bandService.updateActives(bandActiveUpdatingList.stream());
        assertTrue(this.bandPersistence.read("Foo Fighters").getActive());
    }
}
