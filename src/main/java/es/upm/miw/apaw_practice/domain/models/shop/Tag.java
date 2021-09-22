package es.upm.miw.apaw_practice.domain.models.shop;

import java.util.List;
import java.util.stream.Collectors;

public class Tag {
    private String name;
    private String description;
    private List<Article> articles;
    private Boolean favourite;

    public Tag() {
        //empty for framework
    }

    public Tag(String name, String description, List<Article> articles, Boolean favourite) {
        this.name = name;
        this.description = description;
        this.articles = articles;
        this.favourite = favourite;
    }

    public static Tag ofArticleBarcode(Tag tag) {
        tag.setArticles(
                tag.articles.stream()
                        .map(Article::ofBarcode)
                        .collect(Collectors.toList())
        );
        return tag;
    }

    public static Tag ofNameArticleBarcode(Tag tag) {
        Tag tagDto = new Tag();
        tagDto.setName(tag.getName());
        tagDto.setArticles(
                tag.articles.stream()
                        .map(Article::ofBarcode)
                        .collect(Collectors.toList())
        );
        return tagDto;
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

    public List<Article> getArticles() {
        return this.articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
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
                ", articlesBarcode=" + articles.stream().map(Article::getBarcode).collect(Collectors.toList()) +
                ", favourite=" + favourite +
                '}';
    }
}
