package es.upm.miw.apaw_practice.adapters.rest.delivery_food;

import es.upm.miw.apaw_practice.domain.models.delivery_food.Menu;
import es.upm.miw.apaw_practice.domain.services.delivery_food.MenuService;
import org.springframework.web.bind.annotation.*;

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

    @PutMapping(NAME_ID)
    public Menu update(@PathVariable String name, @RequestBody Menu menu){
        return this.menuService.update(name, menu);
    }
}
