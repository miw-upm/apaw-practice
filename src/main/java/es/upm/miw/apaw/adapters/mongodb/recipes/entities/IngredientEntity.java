package es.upm.miw.apaw.adapters.mongodb.recipes.entities;

import es.upm.miw.apaw.domain.models.recipes.Ingredient;

import lombok.*;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.UUID;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Document
public class IngredientEntity {
    @Id
    private UUID id;
    @EqualsAndHashCode.Include
    @Indexed(unique = true)
    private String label;
    private String measurementUnit;
    private Double unitQuantity;
    private BigDecimal marketPrice;

    public Ingredient toIngredient() {
        Ingredient ingredient = new Ingredient();
        BeanUtils.copyProperties(this, ingredient);
        return ingredient;
    }
}
