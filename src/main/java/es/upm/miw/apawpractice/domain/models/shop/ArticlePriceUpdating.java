package es.upm.miw.apawpractice.domain.models.shop;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArticlePriceUpdating {
    private String barcode;
    private BigDecimal price;
}
