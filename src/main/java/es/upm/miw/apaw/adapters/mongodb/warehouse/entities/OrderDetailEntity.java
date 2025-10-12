package es.upm.miw.apaw.adapters.mongodb.warehouse.entities;

import es.upm.miw.apaw.domain.models.warehouse.OrderDetail;
import es.upm.miw.apaw.domain.models.warehouse.ProductItem;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.UUID;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Document

public class OrderDetailEntity {

    @Id
    @EqualsAndHashCode.Include
    private UUID id;

    private Integer     qtyRequested;
    private Integer     qtyMoved;
    private BigDecimal  unitCost;

    @DBRef
    @NotNull
    private ProductItemEntity productItemEntity;

    public OrderDetailEntity(OrderDetail orderDetail) {
        BeanUtils.copyProperties(orderDetail, this, "productItemEntity");
        this.id = UUID.randomUUID();
        this.productItemEntity = new ProductItemEntity(orderDetail.getProductItem());
    }

    public OrderDetail toOrderDetail() {
        ProductItem productItem = null;
        if (this.productItemEntity != null) {
            productItem = this.productItemEntity.toProductItem();
        }

        return OrderDetail.builder()
                .qtyRequested(this.qtyRequested)
                .qtyMoved(this.qtyMoved)
                .unitCost(this.unitCost)
                .productItem(productItem)
                .build();
    }
}
