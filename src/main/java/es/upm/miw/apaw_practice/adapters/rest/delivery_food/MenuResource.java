package es.upm.miw.apaw_practice.adapters.rest.delivery_food;

import es.upm.miw.apaw_practice.domain.services.delivery_food.MenuService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(MenuResource.MENU)
public class MenuResource {

    public static final String MENU = "/delivery_food/menu";
    static final String NAME_ID = "/{name}";

    private final MenuService menuService;

    public MenuResource(MenuService menuService) {
        this.menuService = menuService;
    }

    @DeleteMapping(NAME_ID)
    public void delete(@PathVariable String name) {
        this.menuService.delete(name);
    }
}
