package es.upm.miw.apaw_practice.adapters.mongodb.pharmacy.persistence;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.domain.models.pharmacy.ActiveIngredient;
import es.upm.miw.apaw_practice.domain.models.pharmacy.Drug;
import es.upm.miw.apaw_practice.domain.models.pharmacy.Pharmacy;
import es.upm.miw.apaw_practice.domain.persistence_ports.pharmacy.ActiveIngredientPersistence;
import es.upm.miw.apaw_practice.domain.persistence_ports.pharmacy.PharmacyPersistence;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestConfig
public class ActiveIngredientPersistenceMongodbIT {

    @Autowired
    ActiveIngredientPersistence activeIngredientPersistence;

    @Test
    void testReadByDrugBarcode() {
        List<ActiveIngredient> activeIngredients = this.activeIngredientPersistence.readByDrugBarcode("A9005")
                .collect(Collectors.toList());
        assertEquals("B9005", activeIngredients.get(0).getCode());
    }

}
