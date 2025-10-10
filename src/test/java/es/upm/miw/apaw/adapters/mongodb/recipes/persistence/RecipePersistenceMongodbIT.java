package es.upm.miw.apaw.adapters.mongodb.recipes.persistence;

import es.upm.miw.apaw.adapters.mongodb.recipes.daos.RecipesSeeder;
import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.models.recipes.Recipe;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
class RecipePersistenceMongodbIT {
    @Autowired
    private RecipePersistenceMongodb recipePersistenceMongodb;

    @Autowired
    private RecipesSeeder recipesSeeder;

    @BeforeEach
    void setUp() {
        recipesSeeder.deleteAll();
        recipesSeeder.seedDatabase();
    }

    @Test
    void testReadAllRecipes() {
        List<Recipe> recipes = this.recipePersistenceMongodb.readAll().toList();

        assertThat(recipes)
                .isNotEmpty()
                .hasSizeGreaterThanOrEqualTo(6);

        Optional<Recipe> butterCookies = recipes.stream()
                .filter(r -> "Butter Cookies".equals(r.getTitle()))
                .findFirst();

        assertThat(butterCookies).isPresent();
        Recipe recipe = butterCookies.get();

        assertThat(recipe.getReferenceNumber()).isEqualTo("1");
        assertThat(recipe.getTitle()).isEqualTo("Butter Cookies");
        assertThat(recipe.getServings()).isEqualTo(6);
        assertThat(recipe.getItems()).hasSize(3);
    }

    @Test
    void testReadByReferenceNumber() {
        Recipe recipe = recipePersistenceMongodb.readByReferenceNumber("1");

        assertThat(recipe).isNotNull();
        assertThat(recipe.getReferenceNumber()).isEqualTo("1");
        assertThat(recipe.getTitle()).isEqualTo("Butter Cookies");
        assertThat(recipe.getItems()).hasSize(3);
    }

    @Test
    void testReadByReferenceNumberNotFound() {
        assertThatThrownBy(() -> recipePersistenceMongodb.readByReferenceNumber("999"))
                .isInstanceOf(NotFoundException.class)
                .hasMessageContaining("Recipe reference number: 999");
    }

    @Test
    void testDeleteRecipe() {
        Recipe recipe = recipePersistenceMongodb.readByReferenceNumber("2");
        assertThat(recipe).isNotNull();

        recipePersistenceMongodb.delete("2");
        assertThatThrownBy(() -> recipePersistenceMongodb.readByReferenceNumber("2"))
                .isInstanceOf(NotFoundException.class);
    }
}
