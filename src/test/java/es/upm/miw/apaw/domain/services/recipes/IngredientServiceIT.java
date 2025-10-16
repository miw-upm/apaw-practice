package es.upm.miw.apaw.domain.services.recipes;

import es.upm.miw.apaw.domain.models.recipes.Ingredient;
import es.upm.miw.apaw.domain.persistenceports.recipes.IngredientPersistence;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.math.BigDecimal;
import java.util.stream.Stream;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest
@ActiveProfiles("test")
class IngredientServiceIT {

    @Autowired
    private IngredientService ingredientService;

    @MockitoBean
    private IngredientPersistence ingredientPersistence;

    @Test
    void testUpdatePricesSuccess() {
        Ingredient flour = Ingredient.builder()
                .label("Flour")
                .marketPrice(new BigDecimal("1.20"))
                .build();
        Ingredient sugar = Ingredient.builder()
                .label("Sugar")
                .marketPrice(new BigDecimal("0.90"))
                .build();

        Ingredient flourUpdated = Ingredient.builder()
                .label("Flour")
                .marketPrice(new BigDecimal("2.00"))
                .build();
        Ingredient sugarUpdated = Ingredient.builder()
                .label("Sugar")
                .marketPrice(new BigDecimal("1.50"))
                .build();

        BDDMockito.given(this.ingredientPersistence.readByLabel("Flour")).willReturn(flour);
        BDDMockito.given(this.ingredientPersistence.readByLabel("Sugar")).willReturn(sugar);

        this.ingredientService.updatePrices(Stream.of(flourUpdated, sugarUpdated));

        verify(this.ingredientPersistence, times(1)).update(flour);
        verify(this.ingredientPersistence, times(1)).update(sugar);

        org.assertj.core.api.Assertions.assertThat(flour.getMarketPrice()).isEqualByComparingTo("2.00");
        org.assertj.core.api.Assertions.assertThat(sugar.getMarketPrice()).isEqualByComparingTo("1.50");
    }

    @Test
    void testUpdatePricesWhenIngredientNotFound() {
        Ingredient unknown = Ingredient.builder()
                .label("Vanilla")
                .marketPrice(new BigDecimal("5.00"))
                .build();

        BDDMockito.given(this.ingredientPersistence.readByLabel("Vanilla"))
                .willThrow(new RuntimeException("Ingredient label: Vanilla not found"));

        org.assertj.core.api.Assertions.assertThatThrownBy(() ->
                this.ingredientService.updatePrices(Stream.of(unknown))
        ).hasMessageContaining("Ingredient label: Vanilla not found");
    }
}
