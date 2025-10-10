package es.upm.miw.apaw.adapters.mongodb.recipes.daos;

import es.upm.miw.apaw.adapters.mongodb.recipes.entities.RecipeEntity;
import es.upm.miw.apaw.adapters.mongodb.recipes.entities.RecipeItemEntity;
import es.upm.miw.apaw.adapters.mongodb.recipes.entities.IngredientEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
class RecipeRepositoryIT {

    @Autowired
    private RecipeRepository recipeRepository;

    private RecipeEntity recipe1;
    private RecipeEntity recipe2;

    @BeforeEach
    void setUp() {
        recipeRepository.deleteAll();

        IngredientEntity butter = IngredientEntity.builder()
                .id(UUID.randomUUID())
                .label("Butter")
                .measurementUnit("g")
                .unitQuantity(250.0)
                .marketPrice(new BigDecimal("2.50"))
                .build();

        IngredientEntity sugar = IngredientEntity.builder()
                .id(UUID.randomUUID())
                .label("Sugar")
                .measurementUnit("g")
                .unitQuantity(150.0)
                .marketPrice(new BigDecimal("0.75"))
                .build();

        RecipeItemEntity item1 = RecipeItemEntity.builder()
                .ingredientEntity(butter)
                .quantity(150.0)
                .specifications("Room temperature")
                .build();

        RecipeItemEntity item2 = RecipeItemEntity.builder()
                .ingredientEntity(sugar)
                .quantity(100.0)
                .specifications("Granulated")
                .build();

        recipe1 = RecipeEntity.builder()
                .id(UUID.randomUUID())
                .referenceNumber("1")
                .title("Butter Cookies")
                .instructions("Cream butter and sugar, bake.")
                .servings(6)
                .itemEntities(List.of(item1, item2))
                .build();

        recipe2 = RecipeEntity.builder()
                .id(UUID.randomUUID())
                .referenceNumber("2")
                .title("Omelette")
                .instructions("Whisk eggs and cook.")
                .servings(2)
                .itemEntities(List.of())
                .build();

        recipeRepository.saveAll(List.of(recipe1, recipe2));
    }

    @Test
    void testReadByReferenceNumber() {
        Optional<RecipeEntity> recipeOptional = this.recipeRepository.readByReferenceNumber("1");
        assertThat(recipeOptional).isPresent();

        RecipeEntity recipe = recipeOptional.get();
        assertThat(recipe.getId()).isNotNull();
        assertThat(recipe.getReferenceNumber()).isEqualTo("1");
        assertThat(recipe.getTitle()).isEqualTo("Butter Cookies");
        assertThat(recipe.getItemEntities()).hasSize(2);
    }

    @Test
    void testReadAll() {
        assertThat(this.recipeRepository.findAll())
                .isNotEmpty()
                .anySatisfy(recipe -> {
                    assertThat(recipe.getReferenceNumber()).isNotBlank();
                    assertThat(recipe.getTitle()).isNotBlank();
                    assertThat(recipe.getInstructions()).isNotBlank();
                    assertThat(recipe.getServings()).isNotNull();
                });
    }

    @Test
    void testDeleteByReferenceNumber() {
        assertThat(this.recipeRepository.readByReferenceNumber("2")).isPresent();

        this.recipeRepository.deleteByReferenceNumber("2");
        assertThat(this.recipeRepository.readByReferenceNumber("2")).isNotPresent();
    }
}
