package es.upm.miw.apaw.adapters.mongodb.recipes.entities;

import es.upm.miw.apaw.adapters.mongodb.shop.entities.ArticleItemEntity;
import es.upm.miw.apaw.domain.models.recipes.Recipe;

import lombok.*;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.UUID;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Document
public class RecipeEntity {
    @Id
    private UUID id;
    @EqualsAndHashCode.Include
    @Indexed(unique = true)
    private String referenceNumber;
    private String title;
    private String instructions;
    private Integer servings;
    private List<RecipeItemEntity> itemEntities;

    public RecipeEntity(Recipe recipe) {
        this.id = UUID.randomUUID();
        BeanUtils.copyProperties(recipe, this, "itemEntities");
        this.itemEntities = recipe.getItems().stream()
                .map(RecipeItemEntity::new)
                .toList();
    }

    public Recipe toRecipe() {
        Recipe recipe = new Recipe();
        BeanUtils.copyProperties(this, recipe, "itemEntities");
        recipe.setItems(
                this.itemEntities.stream()
                        .map(RecipeItemEntity::toRecipeItem)
                        .toList()
        );
        return recipe;
    }
}
