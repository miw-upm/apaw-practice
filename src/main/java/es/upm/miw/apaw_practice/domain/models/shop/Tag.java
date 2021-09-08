package es.upm.miw.apaw_practice.domain.models.shop;

import java.util.List;

public class Tag {
    private String name;
    private String description;
    private List< String > articlesBarcode;
    private Boolean favourite;

    public Tag() {
        //empty for framework
    }

    public Tag(String name, String description, List< String > articlesBarcode, Boolean favourite) {
        this.name = name;
        this.description = description;
        this.articlesBarcode = articlesBarcode;
        this.favourite = favourite;
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

    public List< String > getArticlesBarcode() {
        return articlesBarcode;
    }

    public void setArticlesBarcode(List< String > articlesBarcode) {
        this.articlesBarcode = articlesBarcode;
    }

    public Boolean getFavourite() {
        return favourite;
    }

    public void setFavourite(Boolean favourite) {
        this.favourite = favourite;
    }

    @Override
    public String toString() {
        return "Tag{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", articlesBarcode=" + articlesBarcode +
                ", favourite=" + favourite +
                '}';
    }
}
