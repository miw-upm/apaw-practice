package es.upm.miw.apaw.adapters.mongodb.recipes.persistence;

import es.upm.miw.apaw.adapters.mongodb.recipes.daos.RecipesSeeder;
import es.upm.miw.apaw.domain.models.recipes.Menu;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
class MenuPersistenceMongodbIT {

    @Autowired
    private MenuPersistenceMongodb menuPersistenceMongodb;

    @Autowired
    private RecipesSeeder recipesSeeder;

    @Test
    void testFindAllMenus() {
        List<Menu> menus = this.menuPersistenceMongodb.findAll().toList();

        assertThat(menus)
                .isNotEmpty()
                .hasSizeGreaterThanOrEqualTo(3);

        Optional<Menu> christmasMenu = menus.stream()
                .filter(m -> "Christmas".equals(m.getCaption()))
                .findFirst();

        assertThat(christmasMenu).isPresent();
        Menu menu = christmasMenu.get();

        assertThat(menu.getUser().getId()).isNotNull();
        assertThat(menu.getInternalCode()).isEqualTo(1L);
        assertThat(menu.getCaption()).isEqualTo("Christmas");
        assertThat(menu.getType()).isEqualTo("Vegetarian");
        assertThat(menu.getStartDate()).isEqualTo(LocalDate.of(2025, 10, 5));
        assertThat(menu.getUser().getId())
                .isEqualTo(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0003"));
        assertThat(menu.getRecipes()).hasSize(2);

        Optional<Menu> thanksgivingMenu = menus.stream()
                .filter(m -> "Thanksgiving".equals(m.getCaption()))
                .findFirst();

        assertThat(thanksgivingMenu).isPresent();
        assertThat(thanksgivingMenu.get().getInternalCode()).isEqualTo(3L);
        assertThat(thanksgivingMenu.get().getType()).isEqualTo("Vegan");
        assertThat(thanksgivingMenu.get().getStartDate()).isEqualTo(LocalDate.of(2025, 11, 27));
    }
}