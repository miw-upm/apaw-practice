package es.upm.miw.apaw.adapters.mongodb.recipes.persistence;

import es.upm.miw.apaw.adapters.mongodb.recipes.daos.IngredientRepository;
import es.upm.miw.apaw.adapters.mongodb.recipes.daos.RecipesSeeder;
import es.upm.miw.apaw.adapters.mongodb.recipes.entities.IngredientEntity;
import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.models.recipes.Ingredient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

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
    void setUp() {
        recipesSeeder.deleteAll();
        recipesSeeder.seedDatabase();
    }

    @Test
    void testReadByLabelExists() {
        IngredientEntity flour = ingredientRepository.readByLabel("Flour").orElseThrow();

        Ingredient ingredient = ingredientPersistenceMongodb.readByLabel("Flour");

        assertThat(ingredient).isNotNull();
        assertThat(ingredient.getLabel()).isEqualTo(flour.getLabel());
        assertThat(ingredient.getMeasurementUnit()).isEqualTo(flour.getMeasurementUnit());
        assertThat(ingredient.getUnitQuantity()).isEqualTo(flour.getUnitQuantity());
    }

    @Test
    void testReadByLabelNotFound() {
        assertThatThrownBy(() -> ingredientPersistenceMongodb.readByLabel("Nonexistent Ingredient"))
                .isInstanceOf(NotFoundException.class)
                .hasMessageContaining("Ingredient label: Nonexistent Ingredient");
    }
}
