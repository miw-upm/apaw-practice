package es.upm.miw.apaw_practice.adapters.mongodb.pharmacy.persistence;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.adapters.mongodb.shop.ShopSeederService;
import es.upm.miw.apaw_practice.domain.models.pharmacy.Dispensing;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestConfig
public class DispensingPersistenceMongodbIT {

    @Autowired
    private DispensingPersistenceMongodb dispensingPersistenceMongodb;

    @Autowired
    private ShopSeederService pharmacySeederService;

    @Test
    void testReadById() {
        Optional<Dispensing> dispensing = this.dispensingPersistenceMongodb.readAll()
                .filter(dispensingItem -> dispensingItem.getDispensingTimestamp()
                        .isEqual(LocalDateTime.of(2021, 7, 2, 21, 34)))
                .findFirst();
        assertTrue(dispensing.isPresent());
        Dispensing findedDispensing = this.dispensingPersistenceMongodb.readById(dispensing.get().getId());
        assertEquals(dispensing.get(), findedDispensing);
    }

    @Test
    void testUpdate() {
        Optional<Dispensing> dispensing = this.dispensingPersistenceMongodb.readAll()
                .filter(dispensingItem -> dispensingItem.getDispensingTimestamp()
                        .isEqual(LocalDateTime.of(2021, 2, 1, 20, 5)))
                .findFirst();
        assertTrue(dispensing.isPresent());
        dispensing.get().setDispensingTimestamp(LocalDateTime.of(2021, 9, 27, 8, 15));
        this.dispensingPersistenceMongodb.update(dispensing.get());
        Dispensing findedDispensing = this.dispensingPersistenceMongodb.readById(dispensing.get().getId());
        assertEquals(dispensing.get().getId(), findedDispensing.getId());
        assertEquals(2, findedDispensing.getActiveIngredients().size());
        assertEquals("A9001", findedDispensing.getActiveIngredients().get(0).getDrug().getBarcode());
        pharmacySeederService.deleteAll();
        pharmacySeederService.seedDatabase();
    }

    @Test
    void testDelete() {
        Optional<Dispensing> dispensing = this.dispensingPersistenceMongodb.readAll()
                .filter(dispensingItem -> dispensingItem.getDispensingTimestamp()
                        .isEqual(LocalDateTime.of(2021, 1, 5, 13, 20)))
                .findFirst();
        assertTrue(dispensing.isPresent());
        this.dispensingPersistenceMongodb.delete(dispensing.get().getId());
        Optional<Dispensing> newSearchDispensing = this.dispensingPersistenceMongodb.readAll()
                .filter(dispensingItem -> dispensingItem.getDispensingTimestamp()
                        .isEqual(LocalDateTime.of(2021, 1, 5, 13, 20)))
                .findFirst();
        assertTrue(newSearchDispensing.isEmpty());
        pharmacySeederService.deleteAll();
        pharmacySeederService.seedDatabase();
    }
}
