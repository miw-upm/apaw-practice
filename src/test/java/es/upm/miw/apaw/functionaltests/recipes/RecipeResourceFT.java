package es.upm.miw.apaw.functionaltests.recipes;

import es.upm.miw.apaw.adapters.resources.recipes.RecipeResource;
import es.upm.miw.apaw.domain.models.recipes.Recipe;
import es.upm.miw.apaw.domain.models.recipes.RecipeItem;
import es.upm.miw.apaw.domain.services.recipes.RecipeService;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
@ActiveProfiles("test")
class RecipeResourceFT {

    @Autowired
    private WebTestClient webTestClient;

    @MockitoBean
    private RecipeService recipeService;

    @Test
    void testDeleteRecipe() {
        String referenceNumber = "1";

        BDDMockito.doNothing().when(recipeService).delete(referenceNumber);

        webTestClient.delete()
                .uri(RecipeResource.RECIPES + "/" + referenceNumber)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk();

        BDDMockito.verify(recipeService).delete(referenceNumber);
    }

    @Test
    void testCreateRecipe() {
        Recipe newRecipe = Recipe.builder()
                .referenceNumber("8")
                .title("Homemade Pancakes")
                .instructions("Mix ingredients, cook on pan, and serve.")
                .servings(6)
                .build();

        BDDMockito.given(this.recipeService.create(newRecipe)).willReturn(newRecipe);

        webTestClient.post()
                .uri(RecipeResource.RECIPES)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(newRecipe)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.referenceNumber").isEqualTo("8")
                .jsonPath("$.title").isEqualTo("Homemade Pancakes")
                .jsonPath("$.servings").isEqualTo(6);

        BDDMockito.verify(this.recipeService).create(newRecipe);
    }

    @Test
    void testUpdateRecipeItems() {
        String referenceNumber = "1";

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
                .referenceNumber(referenceNumber)
                .title("Butter Cookies")
                .items(updatedItems)
                .build();

        BDDMockito.given(this.recipeService.updateItems(referenceNumber, updatedItems))
                .willReturn(updatedRecipe);

        webTestClient.put()
                .uri(RecipeResource.RECIPES + "/" + referenceNumber + RecipeResource.RECIPE_ITEMS)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(updatedItems)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.referenceNumber").isEqualTo(referenceNumber)
                .jsonPath("$.title").isEqualTo("Butter Cookies")
                .jsonPath("$.items").isArray()
                .jsonPath("$.items.length()").isEqualTo(2)
                .jsonPath("$.items[0].specifications").isEqualTo("Use fresh butter")
                .jsonPath("$.items[1].optional").isEqualTo(true);

        BDDMockito.verify(this.recipeService).updateItems(referenceNumber, updatedItems);
    }
}
