package es.upm.miw.apaw.functionaltests.recipes;

import es.upm.miw.apaw.adapters.resources.recipes.RecipeResource;
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
}
