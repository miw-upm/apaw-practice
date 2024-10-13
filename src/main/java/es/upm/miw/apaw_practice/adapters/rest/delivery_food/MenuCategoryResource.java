package es.upm.miw.apaw_practice.adapters.rest.delivery_food;

import es.upm.miw.apaw_practice.domain.models.delivery_food.MenuCategory;
import es.upm.miw.apaw_practice.domain.services.delivery_food.MenuCategoryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Stream;

@RestController
@RequestMapping(MenuCategoryResource.MENU_CATEGORIES)
public class MenuCategoryResource {
    public static final String MENU_CATEGORIES = "/delivery_food/menu_categories";
    public static final String ACTIVE = "/{active}";
    public static final String NAME = "/name";

    private final MenuCategoryService menuCategoryService;

    public MenuCategoryResource(MenuCategoryService menuCategoryService) {
        this.menuCategoryService = menuCategoryService;
    }

    @GetMapping(value = ACTIVE + NAME)
    public Stream<MenuCategory> findNameByActive(@PathVariable Boolean active){
        return this.menuCategoryService.findByActive(active)
                .map(MenuCategory::ofName);
    }
}
