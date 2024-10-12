package es.upm.miw.apaw_practice.adapters.rest.shopping_center;

import es.upm.miw.apaw_practice.adapters.rest.LexicalAnalyzer;
import es.upm.miw.apaw_practice.domain.models.shopping_center.Shop;
import es.upm.miw.apaw_practice.domain.services.shopping_center.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ShopResource.SHOPS)
public class ShopResource {
    static final String SHOPS = "/shopping_center/shops";

    private final ShopService shopService;

    @Autowired
    public ShopResource(ShopService shopService) {
        this.shopService = shopService;
    }

    @GetMapping
    public Shop findShopById(@RequestParam String q) {
        String id = new LexicalAnalyzer().extractWithAssure(q, "id");
        return this.shopService.findById(id);
    }
}
