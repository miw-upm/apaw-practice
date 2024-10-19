package es.upm.miw.apaw_practice.adapters.rest.e_commerce;


import es.upm.miw.apaw_practice.domain.services.e_commerce.ShoppingCartECommerceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ShoppingCartECommerceResource.SHOPPING_CARTS)
public class ShoppingCartECommerceResource {

    static final String SHOPPING_CARTS = "/e-commerce/shopping-carts";
    static final String SHOPPING_NUM = "/{shoppingNum}";

    private final ShoppingCartECommerceService shoppingCartECommerceService;

    @Autowired
    public ShoppingCartECommerceResource(ShoppingCartECommerceService shoppingCartECommerceService) {
        this.shoppingCartECommerceService = shoppingCartECommerceService;
    }

    @DeleteMapping("/{shoppingNum}")
    public ResponseEntity<Void> delete(@PathVariable Integer shoppingNum) {
        this.shoppingCartECommerceService.delete(shoppingNum);
        return ResponseEntity.noContent().build();
    }
}
