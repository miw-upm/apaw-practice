package es.upm.miw.apawpractice.domain.models.shop;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Article {
    private String barcode;
    private String summary;
    private BigDecimal price;
    private LocalDate registrationDate;
    private String provider;

    public static Article ofBarcode(Article article) {
        Article articleDto = new Article();
        articleDto.setBarcode(article.getBarcode());
        return articleDto;
    }

    public void doDefault() {
        if (Objects.isNull(provider)) {
            this.provider = "various";
        }
    }

}
