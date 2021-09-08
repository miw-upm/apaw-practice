package es.upm.miw.apaw_practice.adapters.mongodb.shop.entities;

import es.upm.miw.apaw_practice.domain.models.shop.Tag;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.stream.Collectors;

@Document
public class TagEntity {
    @Id
    private String id;
    private String description;
    @DBRef
    private List<ArticleEntity> articleEntities;
    private Boolean favourite;

    public TagEntity(String id, String description, List<ArticleEntity> articleEntities, Boolean favourite) {
        this.id = id;
        this.description = description;
        this.articleEntities = articleEntities;
        this.favourite = favourite;
    }

    public String getId() {
        return id;
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
        List<Long> barcodes = this.articleEntities.stream()
                .map(ArticleEntity::getBarcode)
                .collect(Collectors.toList());
        return new Tag(id, description, barcodes, favourite);
    }

    @Override
    public int hashCode() {
        return this.description.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return this == obj || obj != null && getClass() == obj.getClass() && (description.equals(((TagEntity) obj).description));
    }

    @Override
    public String toString() {
        return "TagEntity{" +
                "id='" + id + '\'' +
                ", description='" + description + '\'' +
                ", articleEntities=" + articleEntities +
                ", favourite=" + favourite +
                '}';
    }
}
