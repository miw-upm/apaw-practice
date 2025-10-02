package es.upm.miw.apaw.adapters.mongodb.warehouse.entities;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.UUID;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Document

public class ProductItemEntity {

    @Id
    private UUID        idProductItem;

    @Indexed(unique = true)
    private String      barcodeProductItem;

    private String      nameProductItem;
    private BigDecimal  priceProductItem;
    private String      unitOfMeasure;

}
