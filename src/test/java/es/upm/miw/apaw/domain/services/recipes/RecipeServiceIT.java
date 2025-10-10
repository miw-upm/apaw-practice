package es.upm.miw.apaw.domain.services.recipes;

import es.upm.miw.apaw.domain.models.recipes.Recipe;
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
}
