package es.upm.miw.apaw.functionaltests.recipes;

import es.upm.miw.apaw.adapters.resources.recipes.MenuResource;
import es.upm.miw.apaw.domain.models.UserDto;
import es.upm.miw.apaw.domain.models.recipes.Menu;
import es.upm.miw.apaw.domain.models.recipes.Recipe;
import es.upm.miw.apaw.domain.services.recipes.MenuService;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
@ActiveProfiles("test")
class MenuResourceFT {

    @Autowired
    private WebTestClient webTestClient;

    @MockitoBean
    private MenuService menuService;

    @Test
    void testFindAllMenus() {
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

        BDDMockito.given(this.menuService.getAllMenus())
                .willReturn(Stream.of(christmasMenu, easterMenu));

        webTestClient.get()
                .uri(MenuResource.MENUS)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Menu.class)
                .value(menus -> {
                    assertThat(menus).hasSize(2);
                    assertThat(menus).extracting(Menu::getCaption)
                            .containsExactlyInAnyOrder("Christmas", "Easter");

                    assertThat(menus.getFirst().getUser()).isNotNull();
                    assertThat(menus.getFirst().getUser().getId())
                            .isEqualTo(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0003"));

                    assertThat(menus.getFirst().getRecipes()).hasSizeGreaterThanOrEqualTo(1);
                });
    }
}
