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
        assertTrue(dispensing.get().equals(findedDispensing));
    }

    @Test
    void testUpdateEmptyActiveIngredients() {
        Optional<Dispensing> dispensing = this.dispensingPersistenceMongodb.readAll()
                .filter(dispensingItem -> dispensingItem.getDispensingTimestamp()
                        .isEqual(LocalDateTime.of(2021, 7, 2, 21, 34)))
                .findFirst();
        assertTrue(dispensing.isPresent());
        dispensing.get().setDispensingTimestamp(LocalDateTime.of(2023, 7, 2, 21, 34));
        dispensing.get().getActiveIngredients().clear();
        this.dispensingPersistenceMongodb.update(dispensing.get());
        Dispensing findedDispensing = this.dispensingPersistenceMongodb.readById(dispensing.get().getId());
        assertEquals(dispensing.get().getId(), findedDispensing.getId());
        assertEquals(0, findedDispensing.getActiveIngredients().size());
        assertTrue(findedDispensing.getDispensingTimestamp()
                .isEqual(LocalDateTime.of(2023, 7, 2, 21, 34)));
        pharmacySeederService.deleteAll();
        pharmacySeederService.seedDatabase();
    }

    @Test
    void testUpdateNotNullActiveIngredients() {
        Optional<Dispensing> dispensing = this.dispensingPersistenceMongodb.readAll()
                .filter(dispensingItem -> dispensingItem.getDispensingTimestamp()
                        .isEqual(LocalDateTime.of(2021, 9, 27, 8, 15)))
                .findFirst();
        assertTrue(dispensing.isPresent());
        dispensing.get().setDispensingTimestamp(LocalDateTime.of(2021, 9, 27, 8, 15));
        this.dispensingPersistenceMongodb.update(dispensing.get());
        Dispensing findedDispensing = this.dispensingPersistenceMongodb.readById(dispensing.get().getId());
        assertEquals(dispensing.get().getId(), findedDispensing.getId());
        assertEquals(1, findedDispensing.getActiveIngredients().size());
        assertEquals("A9005", findedDispensing.getActiveIngredients().get(0).getDrug().getBarcode());
        pharmacySeederService.deleteAll();
        pharmacySeederService.seedDatabase();
    }
}
