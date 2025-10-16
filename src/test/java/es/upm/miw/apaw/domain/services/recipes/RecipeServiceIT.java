package es.upm.miw.apaw.domain.services.recipes;

import es.upm.miw.apaw.domain.exceptions.ConflictException;
import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.models.recipes.Recipe;
import es.upm.miw.apaw.domain.models.recipes.RecipeItem;
import es.upm.miw.apaw.domain.persistenceports.recipes.RecipePersistence;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
@ActiveProfiles("test")
class RecipeServiceIT {

    @Autowired
    private RecipeService recipeService;

    @MockitoBean
    private RecipePersistence recipePersistence;

    @Test
    void testReadRecipeByReferenceNumber() {
        Recipe butterCookies = Recipe.builder()
                .title("Butter Cookies")
                .referenceNumber("1")
                .build();

        BDDMockito.given(this.recipePersistence.readByReferenceNumber("1"))
                .willReturn(butterCookies);

        Recipe recipe = this.recipeService.read("1");

        assertThat(recipe).isNotNull();
        assertThat(recipe.getTitle()).isEqualTo("Butter Cookies");
        assertThat(recipe.getReferenceNumber()).isEqualTo("1");
    }

    @Test
    void testReadRecipeNotFound() {
        BDDMockito.given(this.recipePersistence.readByReferenceNumber("999"))
                .willThrow(new RuntimeException("Recipe reference number: 999 not found"));

        assertThatThrownBy(() -> this.recipeService.read("999"))
                .hasMessageContaining("Recipe reference number: 999");
    }

    @Test
    void testDeleteRecipe() {
        BDDMockito.doNothing().when(this.recipePersistence).delete("1");

        this.recipeService.delete("1");
        BDDMockito.verify(this.recipePersistence).delete("1");
    }

    @Test
    void testReadAllRecipes() {
        Recipe recipe1 = Recipe.builder().title("Butter Cookies").referenceNumber("1").build();
        Recipe recipe2 = Recipe.builder().title("Omelette").referenceNumber("2").build();

        BDDMockito.given(this.recipePersistence.readAll())
                .willReturn(Stream.of(recipe1, recipe2));

        List<Recipe> recipes = this.recipeService.readAll().toList();

        assertThat(recipes)
                .isNotEmpty()
                .hasSize(2)
                .extracting(Recipe::getTitle)
                .containsExactlyInAnyOrder("Butter Cookies", "Omelette");
    }

    @Test
    void testCreateRecipeSuccess() {
        Recipe newRecipe = Recipe.builder()
                .title("Homemade Pancakes")
                .referenceNumber("8")
                .build();

        BDDMockito.given(this.recipePersistence.readByReferenceNumber("8"))
                .willThrow(new NotFoundException("Recipe reference number: 8"));

        BDDMockito.given(this.recipePersistence.create(newRecipe))
                .willReturn(newRecipe);

        Recipe created = this.recipeService.create(newRecipe);

        assertThat(created).isNotNull();
        assertThat(created.getReferenceNumber()).isEqualTo("8");
        assertThat(created.getTitle()).isEqualTo("Homemade Pancakes");

        BDDMockito.verify(this.recipePersistence).create(newRecipe);
    }

    @Test
    void testCreateRecipeConflict() {
        Recipe existingRecipe = Recipe.builder()
                .title("Butter Cookies")
                .referenceNumber("1")
                .build();

        BDDMockito.given(this.recipePersistence.readByReferenceNumber("1"))
                .willReturn(existingRecipe);

        Recipe conflictRecipe = Recipe.builder()
                .title("Duplicate Butter Cookies")
                .referenceNumber("1")
                .build();

        assertThatThrownBy(() -> this.recipeService.create(conflictRecipe))
                .isInstanceOf(ConflictException.class)
                .hasMessageContaining("A recipe with reference number '1' already exists.");
    }

    @Test
    void testUpdateItems() {
        Recipe existingRecipe = Recipe.builder()
                .title("Butter Cookies")
                .referenceNumber("1")
                .build();

        BDDMockito.given(this.recipePersistence.readByReferenceNumber("1"))
                .willReturn(existingRecipe);

        RecipeItem item1 = RecipeItem.builder()
                .quantity(200.0)
                .specifications("Use fresh butter")
                .optional(false)
                .build();
        RecipeItem item2 = RecipeItem.builder()
                .quantity(100.0)
                .specifications("Add chocolate chips")
                .optional(true)
                .build();

        List<RecipeItem> updatedItems = List.of(item1, item2);

        Recipe updatedRecipe = Recipe.builder()
                .title("Butter Cookies")
                .referenceNumber("1")
                .items(updatedItems)
                .build();

        BDDMockito.given(this.recipePersistence.update(BDDMockito.any(Recipe.class)))
                .willReturn(updatedRecipe);

        Recipe result = this.recipeService.updateItems("1", updatedItems);

        assertThat(result).isNotNull();
        assertThat(result.getReferenceNumber()).isEqualTo("1");
        assertThat(result.getItems()).hasSize(2);
        assertThat(result.getItems().getFirst().getSpecifications())
                .isEqualTo("Use fresh butter");

        BDDMockito.verify(this.recipePersistence).readByReferenceNumber("1");
        BDDMockito.verify(this.recipePersistence).update(BDDMockito.any(Recipe.class));
    }
}
