package es.upm.miw.apawpractice.adapters.mongodb.shop.entities;

import es.upm.miw.apawpractice.domain.models.shop.Article;
import lombok.*;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Document
public class ArticleEntity {
    @Id
    private String id;
    @EqualsAndHashCode.Include
    @Indexed(unique = true)
    private String barcode;
    private String summary;
    private BigDecimal price;
    private LocalDate registrationDate;
    private String provider;

    public ArticleEntity(Article article) {
        BeanUtils.copyProperties(article, this);
        this.id = UUID.randomUUID().toString();
    }

    public void fromArticle(Article article) {
        BeanUtils.copyProperties(article, this);
    }

    public Article toArticle() {
        Article article = new Article();
        BeanUtils.copyProperties(this, article);
        return article;
    }

}
