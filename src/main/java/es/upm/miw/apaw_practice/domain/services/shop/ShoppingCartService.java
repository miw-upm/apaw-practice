package es.upm.miw.apaw_practice.domain.services.shop;

import es.upm.miw.apaw_practice.domain.models.shop.ArticleItem;
import es.upm.miw.apaw_practice.domain.models.shop.ShoppingCart;
import es.upm.miw.apaw_practice.domain.persistence_ports.shop.ArticlePersistence;
import es.upm.miw.apaw_practice.domain.persistence_ports.shop.ShoppingCartPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.stream.Stream;

@Service
public class ShoppingCartService {

    private final ShoppingCartPersistence shoppingCartPersistence;
    private final ArticlePersistence articlePersistence;

    @Autowired
    public ShoppingCartService(ShoppingCartPersistence shoppingCartPersistence, ArticlePersistence articlePersistence) {
        this.shoppingCartPersistence = shoppingCartPersistence;
        this.articlePersistence = articlePersistence;
    }

    public ShoppingCart updateArticleItems(String id, List<ArticleItem> articleItemList) {
        ShoppingCart shoppingCart = this.shoppingCartPersistence.readById(id);
        shoppingCart.setArticleItems(articleItemList);
        return this.shoppingCartPersistence.update(shoppingCart);
    }

    private BigDecimal total(ShoppingCart shoppingCart) {
        return shoppingCart.getArticleItems().stream()
                .map(articleItem -> {
                    BigDecimal discount = BigDecimal.ONE.subtract(
                            articleItem.getDiscount().divide(new BigDecimal(100), 4, RoundingMode.HALF_UP)
                    );
                    BigDecimal articlePrice = this.articlePersistence.read(articleItem.getArticle().getBarcode()).getPrice();
                    return articlePrice.multiply(BigDecimal.valueOf(articleItem.getAmount())
                            .multiply(discount)
                    );
                })
                .reduce(BigDecimal.ZERO, BigDecimal::add);

    }

    public Stream<ShoppingCart> findByPriceGreaterThan(BigDecimal price) {
        return this.shoppingCartPersistence.readAll()
                .filter(shoppingCart -> price.compareTo(this.total(shoppingCart)) < 0);
    }
}
