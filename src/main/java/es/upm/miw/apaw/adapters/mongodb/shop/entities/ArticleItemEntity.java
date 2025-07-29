package es.upm.miw.apaw.adapters.mongodb.shop.entities;

import es.upm.miw.apaw.domain.models.shop.ArticleItem;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;
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

    public ArticleItemEntity(ArticleItem articleItem) {
        BeanUtils.copyProperties(articleItem, this);
    }

    public ArticleItem toArticleItem() {
        return new ArticleItem(this.articleEntity.toArticle(), this.amount, this.discount);
    }

}
