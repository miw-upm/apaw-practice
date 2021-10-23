package es.upm.miw.apaw_practice.domain.services.pharmacy;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.domain.models.pharmacy.ActiveIngredient;
import es.upm.miw.apaw_practice.domain.models.shop.ArticlePriceUpdating;
import es.upm.miw.apaw_practice.domain.persistence_ports.pharmacy.ActiveIngredientPersistence;
import es.upm.miw.apaw_practice.domain.persistence_ports.shop.ArticlePersistence;
import es.upm.miw.apaw_practice.domain.services.shop.ArticleService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestConfig
class ActiveIngredientServiceIT {

    @Autowired
    private ActiveIngredientService activeIngredientService;

    @Autowired
    private ActiveIngredientPersistence activeIngredientPersistence;

    @Test
    void testFindByPharmacyAndByRepetition() {
        Stream<ActiveIngredient> activeIngredients = this.activeIngredientService.findByPharmacyAndByRepetition("123456",false);
        assertEquals(3, activeIngredients.count());
    }

}
