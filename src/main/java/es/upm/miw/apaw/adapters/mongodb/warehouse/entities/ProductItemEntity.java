package es.upm.miw.apaw.adapters.mongodb.warehouse.entities;

import es.upm.miw.apaw.domain.models.warehouse.ProductItem;
import lombok.*;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Document

public class ProductItemEntity {

    @Id
    private UUID        id;
    @EqualsAndHashCode.Include
    @Indexed(unique = true)
    private String      barcode;
    private String      appoint;    //name / description
    private BigDecimal  cost;
    private String      unitOfMeasure;

    public ProductItemEntity(ProductItem productItem) {
        BeanUtils.copyProperties(productItem, this);
        this.id = UUID.randomUUID();
    }

    public void fromProductItem(ProductItem productItem) {
        BeanUtils.copyProperties(productItem, this);
    }

    public ProductItem toProductItem() {
        ProductItem productItem = new ProductItem();
        BeanUtils.copyProperties(this, productItem);
        return productItem;
    }

}
