package es.upm.miw.apaw.domain.models.shop;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArticleItem {
    private Article article;
    private Integer amount;
    private BigDecimal discount;
}
