package es.upm.miw.apaw.functionaltests.recipes;

import es.upm.miw.apaw.adapters.resources.recipes.RecipeResource;
import es.upm.miw.apaw.domain.models.recipes.Recipe;
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

}
