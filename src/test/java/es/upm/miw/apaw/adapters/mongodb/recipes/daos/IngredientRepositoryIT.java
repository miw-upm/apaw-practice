package es.upm.miw.apaw.adapters.mongodb.recipes.daos;

import es.upm.miw.apaw.adapters.mongodb.recipes.entities.IngredientEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@ActiveProfiles("test")
class IngredientRepositoryIT {

    @Autowired
    private IngredientRepository ingredientRepository;

    @Test
    void testReadByLabel() {
        assertTrue(this.ingredientRepository.readByLabel("Milk").isPresent());
        IngredientEntity ingredient = this.ingredientRepository.readByLabel("Milk").get();
        assertThat(ingredient.getMeasurementUnit()).isEqualTo("ml");
        assertThat(ingredient.getUnitQuantity()).isEqualTo(1000.0);
        assertThat(ingredient.getMarketPrice()).isEqualTo(new BigDecimal("1.10"));
    }
}
