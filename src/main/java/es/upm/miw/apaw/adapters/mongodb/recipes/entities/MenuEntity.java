package es.upm.miw.apaw.adapters.mongodb.recipes.entities;

import es.upm.miw.apaw.domain.models.UserDto;
import es.upm.miw.apaw.domain.models.recipes.Menu;

import lombok.*;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Document
public class MenuEntity {
    @Id
    private UUID id;
    @EqualsAndHashCode.Include
    @Indexed(unique = true)
    private Long internalCode;
    private String caption;
    private LocalDate startDate;
    private String type;
    @DBRef
    private List<RecipeEntity> recipeEntities;
    private UUID userId;

    public Menu toMenu() {
        Menu menu = new Menu();
        BeanUtils.copyProperties(this, menu, "user", "recipeEntities");
        menu.setUser(UserDto.builder().id(userId).build());
        menu.setRecipes(
                this.recipeEntities.stream()
                .map(RecipeEntity::toRecipe)
                .toList()
        );
        return menu;
    }

}
