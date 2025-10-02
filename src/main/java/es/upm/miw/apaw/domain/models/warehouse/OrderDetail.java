package es.upm.miw.apaw.domain.models.warehouse;


import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor

public class OrderDetail {

    @NotNull
    private UUID idOrderDetail;

    @NotNull
    private UUID idMovementOrder;

    @NotNull
    private UUID idProductItem;

    @NotNull
    private Integer qtyRequested;

    @NotNull
    private Integer qtyMoved;

    private BigDecimal unitCost;
}
