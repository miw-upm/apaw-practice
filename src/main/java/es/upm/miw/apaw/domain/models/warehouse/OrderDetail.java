package es.upm.miw.apaw.domain.models.warehouse;


import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor

public class OrderDetail {


    private ProductItem productItem;

    @NotNull
    private Integer qtyRequested;

    private Integer qtyMoved;

    private BigDecimal unitCost;
}
