package es.upm.miw.apaw.adapters.mongodb.shop.entities;

import es.upm.miw.apaw.domain.models.shop.Article;
import es.upm.miw.apaw.domain.models.shop.Tag;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.UUID;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Document
public class TagEntity {
    @Id
    private String id;
    @EqualsAndHashCode.Include
    @Indexed(unique = true)
    private String name;
    private String description;
    @DBRef
    private List<ArticleEntity> articleEntities;
    private Boolean favourite;

    public TagEntity(String name, String description, List<ArticleEntity> articleEntities, Boolean favourite) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.description = description;
        this.articleEntities = articleEntities;
        this.favourite = favourite;
    }

    public Tag toTag() {
        List<Article> articles = this.articleEntities.stream()
                .map(ArticleEntity::toArticle)
                .toList();
        return new Tag(name, description, articles, favourite);
    }

}
