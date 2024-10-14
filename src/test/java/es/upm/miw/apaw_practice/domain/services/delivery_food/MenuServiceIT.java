package es.upm.miw.apaw_practice.domain.services.delivery_food;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertThrows;

@TestConfig
class MenuServiceIT {

    @Autowired
    private MenuService menuService;

    @Test
    void testDelete() {
        String menuName = "Seafood Platter";
        this.menuService.delete(menuName);
        assertThrows(NotFoundException.class, () -> this.menuService.read(menuName));
    }

}