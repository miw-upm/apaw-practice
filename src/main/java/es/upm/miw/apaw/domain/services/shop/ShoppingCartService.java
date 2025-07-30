package es.upm.miw.apaw.domain.services.shop;

import es.upm.miw.apaw.domain.models.UserDto;
import es.upm.miw.apaw.domain.models.shop.ArticleItem;
import es.upm.miw.apaw.domain.models.shop.ShoppingCart;
import es.upm.miw.apaw.domain.persistenceports.shop.ArticlePersistence;
import es.upm.miw.apaw.domain.persistenceports.shop.ShoppingCartPersistence;
import es.upm.miw.apaw.domain.restclients.UserRestClient;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

@Service
public class ShoppingCartService {

    private final ShoppingCartPersistence shoppingCartPersistence;
    private final ArticlePersistence articlePersistence;
    private final UserRestClient userRestClient;

    @Autowired
    public ShoppingCartService(ShoppingCartPersistence shoppingCartPersistence, ArticlePersistence articlePersistence, UserRestClient userRestClient) {
        this.shoppingCartPersistence = shoppingCartPersistence;
        this.articlePersistence = articlePersistence;
        this.userRestClient = userRestClient;
    }

    public ShoppingCart updateArticleItems(UUID id, List<ArticleItem> articleItemList) {
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

    public ShoppingCart create(@Valid ShoppingCart shoppingCart) {
        shoppingCart.setId(UUID.randomUUID());
        shoppingCart.setCreationDate(LocalDateTime.now());
        UserDto userDto = this.userRestClient.readById(shoppingCart.getUser().getId());
        shoppingCart.setUser(userDto);
        ShoppingCart shoppingCartDb = this.shoppingCartPersistence.create(shoppingCart);
        shoppingCartDb.setUser(userDto);
        return shoppingCartDb;
    }
}
