package es.upm.miw.apaw.adapters.mongodb.recipes.daos;

import es.upm.miw.apaw.domain.services.recipes.RecipeService;
import es.upm.miw.apaw.adapters.mongodb.recipes.entities.RecipeEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
class RecipeRepositoryIT {
    @Autowired
    private RecipeRepository recipeRepository;

    @Test
    void testReadByReferenceNumber() {
        Optional<RecipeEntity> recipeOptional = this.recipeRepository.readByReferenceNumber("1");
        assertThat(recipeOptional).isPresent();

        RecipeEntity recipe = recipeOptional.get();
        assertThat(recipe.getId()).isNotNull();
        assertThat(recipe.getReferenceNumber()).isEqualTo("1");
        assertThat(recipe.getTitle()).isEqualTo("Butter Cookies");
        assertThat(recipe.getItemEntities()).hasSize(3); // matches your seeder
    }

    @Test
    void testReadAll() {
        assertThat(this.recipeRepository.findAll())
                .isNotEmpty()
                .anySatisfy(recipe -> {
                    assertThat(recipe.getReferenceNumber()).isEqualTo("1");
                    assertThat(recipe.getTitle()).isNotBlank();
                    assertThat(recipe.getInstructions()).isNotBlank();
                    assertThat(recipe.getServings()).isNotNull();
                    assertThat(recipe.getItemEntities()).isNotEmpty();
                });
    }

    @Test
    void testDeleteByReferenceNumber() {
        assertThat(this.recipeRepository.readByReferenceNumber("2")).isPresent();

        this.recipeRepository.deleteByReferenceNumber("2");
        assertThat(this.recipeRepository.readByReferenceNumber("2")).isNotPresent();
    }
}
