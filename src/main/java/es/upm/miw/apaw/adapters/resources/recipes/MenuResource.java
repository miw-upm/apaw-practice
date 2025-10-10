package es.upm.miw.apaw.adapters.resources.recipes;

import es.upm.miw.apaw.domain.models.recipes.Menu;
import es.upm.miw.apaw.domain.services.recipes.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Stream;

@RestController
@RequestMapping(MenuResource.MENUS)
public class MenuResource {
    public static final String MENUS = "/recipes/menus";

    private final MenuService menuService;

    @Autowired
    public MenuResource(MenuService menuService) {
        this.menuService = menuService;
    }

    @GetMapping
    public Stream<Menu> findAll() {
        return this.menuService.getAllMenus();
    }
}
