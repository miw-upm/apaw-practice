package es.upm.miw.apaw_practice.adapters.mongodb.shop.entities;

import es.upm.miw.apaw_practice.domain.models.shop.Article;
import es.upm.miw.apaw_practice.domain.models.shop.Tag;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Document
public class TagEntity {
    @Id
    private String id;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<ArticleEntity> getArticleEntities() {
        return articleEntities;
    }

    public void setArticleEntities(List<ArticleEntity> articleEntities) {
        this.articleEntities = articleEntities;
    }

    public Boolean getFavourite() {
        return favourite;
    }

    public void setFavourite(Boolean favourite) {
        this.favourite = favourite;
    }

    public Tag toTag() {
        List<Article> articles = this.articleEntities.stream()
                .map(ArticleEntity::toArticle)
                .collect(Collectors.toList());
        return new Tag(name, description, articles, favourite);
    }

    @Override
    public int hashCode() {
        return this.name.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return this == obj || obj != null && getClass() == obj.getClass() && (name.equals(((TagEntity) obj).name));
    }

    @Override
    public String toString() {
        return "TagEntity{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", articleEntities=" + articleEntities +
                ", favourite=" + favourite +
                '}';
    }
}
