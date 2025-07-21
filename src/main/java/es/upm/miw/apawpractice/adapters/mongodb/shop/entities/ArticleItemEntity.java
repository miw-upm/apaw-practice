package es.upm.miw.apawpractice.adapters.mongodb.shop.entities;

import es.upm.miw.apawpractice.domain.models.shop.ArticleItem;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.math.BigDecimal;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArticleItemEntity {
    @DBRef
    private ArticleEntity articleEntity;
    private Integer amount;
    private BigDecimal discount;

    public ArticleItem toArticleItem() {
        return new ArticleItem(this.articleEntity.toArticle(), this.amount, this.discount);
    }

}
