package es.upm.miw.apaw.adapters.mongodb.shop.entities;

import es.upm.miw.apaw.domain.models.shop.ArticleItem;
import es.upm.miw.apaw.domain.models.shop.ShoppingCart;
import lombok.*;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Document
public class ShoppingCartEntity {
    @EqualsAndHashCode.Include
    @Id
    private String id;
    private LocalDateTime creationDate;
    private List<ArticleItemEntity> articleItemEntities;
    private String user;
    private String address;

    public ShoppingCartEntity(List<ArticleItemEntity> articleItemEntities, String user, String address) {
        this.id = UUID.randomUUID().toString();
        this.creationDate = LocalDateTime.now();
        this.articleItemEntities = articleItemEntities;
        this.user = user;
        this.address = address;
    }

    public ShoppingCart toShoppingCart() {
        ShoppingCart shoppingCart = new ShoppingCart();
        BeanUtils.copyProperties(this, shoppingCart, "articleItemEntities");
        List<ArticleItem> articleItems = this.articleItemEntities.stream()
                .map(ArticleItemEntity::toArticleItem)
                .toList();
        shoppingCart.setArticleItems(articleItems);
        return shoppingCart;

    }
}
