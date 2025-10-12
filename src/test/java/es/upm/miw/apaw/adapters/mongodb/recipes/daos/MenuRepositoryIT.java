package es.upm.miw.apaw.adapters.mongodb.recipes.daos;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
class MenuRepositoryIT {

    @Autowired
    private MenuRepository menuRepository;

    @Test
    void testCreateAndRead() {
        assertThat(this.menuRepository.findAll())
                .anySatisfy(menu -> {
                    assertThat(menu.getUserId())
                            .isEqualTo(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0003"));
                    assertThat(menu.getId())
                            .isNotNull();
                    assertThat(menu.getStartDate())
                            .isNotNull();
                    assertThat(menu.getInternalCode())
                            .isEqualTo(1L);
                    assertThat(menu.getCaption())
                            .isEqualTo("Christmas");
                    assertThat(menu.getType())
                            .isEqualTo("Vegetarian");
                    assertThat(menu.getRecipeEntities())
                            .hasSize(2);
                    assertThat(menu.getRecipeEntities().getFirst())
                            .isNotNull();
                });
    }
}
