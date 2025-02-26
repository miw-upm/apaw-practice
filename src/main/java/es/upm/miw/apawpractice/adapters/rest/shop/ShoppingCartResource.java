package es.upm.miw.apawpractice.adapters.rest.shop;

import es.upm.miw.apawpractice.adapters.rest.LexicalAnalyzer;
import es.upm.miw.apawpractice.domain.models.shop.ArticleItem;
import es.upm.miw.apawpractice.domain.models.shop.ShoppingCart;
import es.upm.miw.apawpractice.domain.services.shop.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Stream;

@RestController
@RequestMapping(ShoppingCartResource.SHOPPING_CARTS)
public class ShoppingCartResource {
    public static final String SHOPPING_CARTS = "/shop/shopping-carts";

    public static final String ID_ID = "/{id}";
    public static final String ARTICLE_ITEMS = "/article-items";
    public static final String SEARCH = "/search";

    private final ShoppingCartService shoppingCartService;

    @Autowired
    public ShoppingCartResource(ShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
    }

    @PutMapping(ID_ID + ARTICLE_ITEMS)
    public ShoppingCart updateArticleItems(@PathVariable String id, @RequestBody List<ArticleItem> articleItemList) {
        return this.shoppingCartService.updateArticleItems(id, articleItemList);
    }

    @GetMapping(SEARCH)
    public Stream<ShoppingCart> findByPriceGreaterThan(@RequestParam String q) {
        BigDecimal price = new LexicalAnalyzer().extractWithAssure(q, "price", BigDecimal::new);
        return this.shoppingCartService.findByPriceGreaterThan(price)
                .map(ShoppingCart::ofIdUser);
    }
}
