package es.upm.miw.apawpractice.domain.models.shop;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Tag {
    private String name;
    private String description;
    private List<Article> articles;
    private Boolean favourite;

    public static Tag ofArticleBarcode(Tag tag) {
        tag.setArticles(
                tag.articles.stream()
                        .map(Article::ofBarcode)
                        .toList()
        );
        return tag;
    }

    public static Tag ofNameArticleBarcode(Tag tag) {
        Tag tagDto = new Tag();
        tagDto.setName(tag.getName());
        tagDto.setArticles(
                tag.articles.stream()
                        .map(Article::ofBarcode)
                        .toList()
        );
        return tagDto;
    }

}
