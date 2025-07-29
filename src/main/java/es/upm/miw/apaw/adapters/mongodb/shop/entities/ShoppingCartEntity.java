package es.upm.miw.apaw.adapters.mongodb.shop.entities;

import es.upm.miw.apaw.domain.models.shop.ArticleItem;
import es.upm.miw.apaw.domain.models.shop.ShoppingCart;
import es.upm.miw.apaw.domain.models.shop.UserDto;
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
    private UUID id;
    private LocalDateTime creationDate;
    private List<ArticleItemEntity> articleItemEntities;
    private UUID userId;

    public ShoppingCartEntity(ShoppingCart shoppingCart) {
        BeanUtils.copyProperties(shoppingCart, this, "user", "articleItem");
        this.userId = shoppingCart.getUser().getId();
        this.articleItemEntities = shoppingCart.getArticleItems().stream()
                .map(ArticleItemEntity::new)
                .toList();
    }

    public ShoppingCart toShoppingCart() {
        ShoppingCart shoppingCart = new ShoppingCart();
        BeanUtils.copyProperties(this, shoppingCart, "user", "articleItemEntities");
        shoppingCart.setUser(UserDto.builder().id(userId).build());
        List<ArticleItem> articleItems = this.articleItemEntities.stream()
                .map(ArticleItemEntity::toArticleItem)
                .toList();
        shoppingCart.setArticleItems(articleItems);
        return shoppingCart;

    }
}
