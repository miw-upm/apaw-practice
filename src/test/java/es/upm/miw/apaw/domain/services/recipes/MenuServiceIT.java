package es.upm.miw.apaw.domain.services.recipes;

import es.upm.miw.apaw.domain.models.UserDto;
import es.upm.miw.apaw.domain.models.recipes.Menu;
import es.upm.miw.apaw.domain.models.recipes.Recipe;
import es.upm.miw.apaw.domain.persistenceports.recipes.MenuPersistence;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
class MenuServiceIT {

    @Autowired
    private MenuService menuService;

    @MockitoBean
    private MenuPersistence menuPersistence;

    @Test
    void testGetAllMenus() {
        // given
        UserDto user = UserDto.builder()
                .id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0003"))
                .firstName("Alice")
                .mobile("123456789")
                .build();

        Recipe recipe1 = Recipe.builder().title("Butter Cookies").referenceNumber("1").build();
        Recipe recipe2 = Recipe.builder().title("Omelette").referenceNumber("2").build();

        Menu christmasMenu = Menu.builder()
                .internalCode(1L)
                .caption("Christmas")
                .type("Vegetarian")
                .startDate(LocalDate.of(2025, 10, 5))
                .user(user)
                .recipes(List.of(recipe1, recipe2))
                .build();

        Menu easterMenu = Menu.builder()
                .internalCode(2L)
                .caption("Easter")
                .type("Traditional")
                .startDate(LocalDate.of(2025, 4, 21))
                .user(user)
                .recipes(List.of(recipe1))
                .build();

        BDDMockito.given(this.menuPersistence.findAll())
                .willReturn(Stream.of(christmasMenu, easterMenu));

        // when
        List<Menu> menus = this.menuService.getAllMenus().toList();

        // then
        assertThat(menus)
                .isNotEmpty()
                .hasSize(2)
                .extracting(Menu::getCaption)
                .containsExactlyInAnyOrder("Christmas", "Easter");

        assertThat(menus.getFirst().getUser())
                .isNotNull()
                .extracting(UserDto::getId, UserDto::getFirstName)
                .containsExactly(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0003"), "Alice");

        assertThat(menus.getFirst().getRecipes())
                .isNotEmpty()
                .allSatisfy(recipe -> assertThat(recipe.getTitle()).isNotNull());
    }
}
