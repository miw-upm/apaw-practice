package es.upm.miw.apaw_practice.domain.models.delivery_food;

import es.upm.miw.apaw_practice.TestConfig;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestConfig
public class MenuTest {

    @Test
    void testBuild(){
        String menuName = "Menu 1";
        String description = "Description 1";
        Double rating = 4.1;

        Menu menu = Menu.builder()
                .name(menuName)
                .description(description)
                .rating(rating)
                .build();

        assertNotNull(menu);
        assertEquals(menuName, menu.getName());
        assertEquals(description, menu.getDescription());
        assertEquals(rating, menu.getRating());
        assertNull(menu.getCategories());
    }

    @Test
    void testBuildOptional(){
        String menuName = "Menu 1";
        String description = "Description 1";
        Double rating = 4.1;

        Menu menu = Menu.builder()
                .name(menuName)
                .description(description)
                .rating(rating)
                .categories(List.of(new MenuCategory("cat 01", "descrip", Boolean.TRUE)))
                .build();

        assertNotNull(menu);
        assertEquals(menuName, menu.getName());
        assertEquals(description, menu.getDescription());
        assertEquals(rating, menu.getRating());
        assertNotNull(menu.getCategories());
    }
}
