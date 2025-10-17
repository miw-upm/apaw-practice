package es.upm.miw.apaw.adapters.mongodb.recipes.persistence;

import es.upm.miw.apaw.adapters.mongodb.recipes.daos.RecipesSeeder;
import es.upm.miw.apaw.domain.models.UserDto;
import es.upm.miw.apaw.domain.models.recipes.Menu;
import es.upm.miw.apaw.domain.restclients.UserRestClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

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

    @MockitoBean
    private UserRestClient userRestClient;

    @BeforeEach
    void seedDatabase() {
        this.recipesSeeder.deleteAll();
        this.recipesSeeder.seedDatabase();
    }

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

    @Test
    void testFindMobilesBySpecifications() {
        String specification = "Melted butter";

        UserDto userDto1 = UserDto.builder().mobile("666000660").build();
        UserDto userDto2 = UserDto.builder().mobile("666000661").build();
        UserDto userDto3 = UserDto.builder().mobile("666000662").build();

        BDDMockito.given(this.userRestClient.readById(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0000")))
                .willReturn(userDto1);
        BDDMockito.given(this.userRestClient.readById(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0003")))
                .willReturn(userDto2);
        BDDMockito.given(this.userRestClient.readById(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0004")))
                .willReturn(userDto3);

        List<String> mobiles = this.menuPersistenceMongodb.findMobilesBySpecifications(specification);

        assertThat(mobiles)
                .isNotEmpty()
                .containsExactly("666000660");

        BDDMockito.then(this.userRestClient)
                .should()
                .readById(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0000"));
    }

    @Test
    void testFindMobilesBySpecifications_NoMatches() {
        String specification = "Nonexistent Specification";
        List<String> mobiles = this.menuPersistenceMongodb.findMobilesBySpecifications(specification);

        assertThat(mobiles).isEmpty();
    }

    @Test
    void testGetUnitQuantitySumByMenuType() {
        String menuType = "Vegetarian";
        double expectedSum = 2912.0;
        Double result = this.menuPersistenceMongodb.getUnitQuantitySumByMenuType(menuType);

        assertThat(result)
                .isNotNull()
                .isEqualTo(expectedSum);
    }

    @Test
    void testGetUnitQuantitySumByMenuType_NoMatches() {
        String menuType = "NonexistentType";

        Double result = this.menuPersistenceMongodb.getUnitQuantitySumByMenuType(menuType);

        assertThat(result)
                .isNotNull()
                .isEqualTo(0.0);
    }
}
