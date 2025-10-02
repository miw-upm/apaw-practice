package es.upm.miw.apaw.adapters.mongodb.warehouse.entities;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Document

public class InventoryEntity {

    @Id
    private UUID            idInventory;

    //ProductItem
    private UUID idProductItem;

    private Integer         currentStock;

    private String          location;
    private LocalDateTime   lastUpdateDate;

}
