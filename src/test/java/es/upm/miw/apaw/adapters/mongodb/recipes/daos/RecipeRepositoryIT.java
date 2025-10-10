package es.upm.miw.apaw.adapters.mongodb.recipes.daos;

import es.upm.miw.apaw.adapters.mongodb.recipes.entities.IngredientEntity;
import es.upm.miw.apaw.adapters.mongodb.recipes.entities.RecipeEntity;
import es.upm.miw.apaw.adapters.mongodb.recipes.entities.RecipeItemEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@SpringBootTest
@ActiveProfiles("test")
class RecipeRepositoryIT {

    @MockBean
    private RecipeRepository recipeRepository; // <-- mockeamos este bean

    private RecipeEntity recipe1;
    private RecipeEntity recipe2;

    @BeforeEach
    void setUp() {
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
    }

    @Test
    void testReadByReferenceNumber() {
        when(recipeRepository.readByReferenceNumber("1")).thenReturn(Optional.of(recipe1));

        Optional<RecipeEntity> recipeOptional = this.recipeRepository.readByReferenceNumber("1");
        assertThat(recipeOptional).isPresent();

        RecipeEntity recipe = recipeOptional.get();
        assertThat(recipe.getReferenceNumber()).isEqualTo("1");
        assertThat(recipe.getTitle()).isEqualTo("Butter Cookies");
        assertThat(recipe.getItemEntities()).hasSize(2);

        verify(recipeRepository, times(1)).readByReferenceNumber("1");
    }

    @Test
    void testDeleteByReferenceNumber() {
        when(recipeRepository.readByReferenceNumber("2"))
                .thenReturn(Optional.of(recipe2))
                .thenReturn(Optional.empty());

        assertThat(this.recipeRepository.readByReferenceNumber("2")).isPresent();

        doNothing().when(recipeRepository).deleteByReferenceNumber("2");

        this.recipeRepository.deleteByReferenceNumber("2");
        verify(recipeRepository, times(1)).deleteByReferenceNumber("2");
    }
}
