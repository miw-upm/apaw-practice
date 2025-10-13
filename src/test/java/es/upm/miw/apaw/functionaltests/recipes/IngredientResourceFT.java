package es.upm.miw.apaw.functionaltests.recipes;

import es.upm.miw.apaw.adapters.resources.recipes.IngredientResource;
import es.upm.miw.apaw.domain.models.recipes.Ingredient;
import es.upm.miw.apaw.domain.services.recipes.IngredientService;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
@ActiveProfiles("test")
class IngredientResourceFT {

    @Autowired
    private WebTestClient webTestClient;

    @MockitoBean
    private IngredientService ingredientService;

    @Test
    void testUpdateIngredientPrices() {
        List<Ingredient> updatedIngredients = List.of(
                Ingredient.builder()
                        .label("Flour")
                        .marketPrice(new BigDecimal("2.50"))
                        .build(),
                Ingredient.builder()
                        .label("Sugar")
                        .marketPrice(new BigDecimal("1.80"))
                        .build()
        );

        BDDMockito.doNothing().when(this.ingredientService)
                .updatePrices(BDDMockito.any(Stream.class));

        webTestClient.patch()
                .uri(IngredientResource.INGREDIENTS)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .bodyValue(updatedIngredients)
                .exchange()
                .expectStatus().isOk();

        ArgumentCaptor<Stream<Ingredient>> captor = ArgumentCaptor.forClass(Stream.class);
        verify(this.ingredientService).updatePrices(captor.capture());

        List<Ingredient> capturedList = captor.getValue().toList();

        assertThat(capturedList)
                .extracting(Ingredient::getLabel)
                .containsExactlyInAnyOrder("Flour", "Sugar");

        assertThat(capturedList)
                .extracting(Ingredient::getMarketPrice)
                .containsExactlyInAnyOrder(new BigDecimal("2.50"), new BigDecimal("1.80"));
    }
}
