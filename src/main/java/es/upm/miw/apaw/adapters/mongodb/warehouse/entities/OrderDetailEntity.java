package es.upm.miw.apaw.adapters.mongodb.warehouse.entities;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.UUID;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Document

public class OrderDetailEntity {

    @Id
    private UUID        idOrderDetail;

    //MovementOrder ID
    private UUID idMovementOrder;

    //ProductItem ID
    private UUID idProductItem;

    private Integer     qtyRequested;
    private Integer     qtyMoved;
    private BigDecimal  unitCost;

}
