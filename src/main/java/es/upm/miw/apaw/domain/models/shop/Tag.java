package es.upm.miw.apaw.domain.models.shop;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Tag {
    @NotNull
    @NotBlank
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
