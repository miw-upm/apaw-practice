package es.upm.miw.apaw.adapters.mongodb.recipes.persistence;

import es.upm.miw.apaw.adapters.mongodb.recipes.daos.IngredientRepository;
import es.upm.miw.apaw.adapters.mongodb.recipes.daos.RecipesSeeder;
import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.models.recipes.Ingredient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
@ActiveProfiles("test")
class IngredientPersistenceMongodbIT {

    @Autowired
    private IngredientPersistenceMongodb ingredientPersistenceMongodb;

    @Autowired
    private IngredientRepository ingredientRepository;

    @Autowired
    private RecipesSeeder recipesSeeder;

    @BeforeEach
    void resetDb() {
        recipesSeeder.deleteAll();
        recipesSeeder.seedDatabase();
    }

    @Test
    void testReadByLabelExists() {
        Ingredient ingredient = this.ingredientPersistenceMongodb.readByLabel("Flour");

        assertThat(ingredient).isNotNull();
        assertThat(ingredient.getLabel()).isEqualTo("Flour");
        assertThat(ingredient.getMeasurementUnit()).isEqualTo("g");
        assertThat(ingredient.getUnitQuantity()).isEqualTo(1000.0);
        assertThat(ingredient.getMarketPrice()).isEqualTo(new BigDecimal("1.20"));
    }

    @Test
    void testReadByLabelNotFound() {
        assertThatThrownBy(() -> this.ingredientPersistenceMongodb.readByLabel("Nonexistent Ingredient"))
                .isInstanceOf(NotFoundException.class)
                .hasMessageContaining("Ingredient label: Nonexistent Ingredient");
    }

    @Test
    void testUpdateExistingIngredient() {
        Ingredient ingredient = this.ingredientPersistenceMongodb.readByLabel("Flour");
        ingredient.setMarketPrice(new BigDecimal("20.99"));

        Ingredient updatedIngredient = ingredientPersistenceMongodb.update(ingredient);
        assertThat(updatedIngredient.getMarketPrice()).isEqualByComparingTo("20.99");
    }

    @Test
    void testUpdateIngredientNotFound() {
        Ingredient nonExistent = Ingredient.builder()
                .label("Nonexistent Ingredient")
                .marketPrice(new BigDecimal("1.99")).build();

        assertThatThrownBy(() -> ingredientPersistenceMongodb.update(nonExistent))
                .isInstanceOf(NotFoundException.class)
                .hasMessageContaining("Ingredient with label: " + nonExistent.getLabel() + " not found");
    }
}
