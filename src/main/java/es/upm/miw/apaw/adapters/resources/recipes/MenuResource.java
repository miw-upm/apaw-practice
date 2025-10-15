package es.upm.miw.apaw.adapters.resources.recipes;

import es.upm.miw.apaw.domain.models.recipes.Menu;
import es.upm.miw.apaw.domain.services.recipes.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Stream;

@RestController
@RequestMapping(MenuResource.MENUS)
public class MenuResource {
    public static final String MENUS = "/recipes/menus";
    public static final String USER_MOBILES = "/user/mobiles";
    public static final String UNIT_QUANTITY_SUM = "/ingredient-unit-quantity-sum";

    private final MenuService menuService;

    @Autowired
    public MenuResource(MenuService menuService) {
        this.menuService = menuService;
    }

    @GetMapping
    public Stream<Menu> findAll() {
        return this.menuService.getAllMenus();
    }

    @GetMapping(USER_MOBILES)
    public List<String> findMobilesBySpecifications(@RequestParam String specifications) {
        return this.menuService.findMobilesBySpecifications(specifications);
    }

    @GetMapping(UNIT_QUANTITY_SUM)
    public Double getUnitQuantitySumByMenuType(@RequestParam String menuType) {
        return this.menuService.getUnitQuantitySumByMenuType(menuType);
    }
}
