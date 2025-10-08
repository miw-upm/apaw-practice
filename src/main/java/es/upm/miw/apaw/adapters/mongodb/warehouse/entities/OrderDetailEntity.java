package es.upm.miw.apaw.adapters.mongodb.warehouse.entities;

import es.upm.miw.apaw.domain.models.warehouse.OrderDetail;
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
    private ProductItemEntity productItemEntity;

    public OrderDetailEntity(OrderDetail orderDetail) {
        BeanUtils.copyProperties(orderDetail, this, "productItemEntity");
        this.id = UUID.randomUUID();

        if (orderDetail.getProductItem() != null) {
            this.productItemEntity = new ProductItemEntity(orderDetail.getProductItem());
        }
    }

    public OrderDetail toOrderDetail() {
        return new OrderDetail(
                this.qtyRequested,
                this.qtyMoved,
                this.unitCost,
                this.productItemEntity != null ? this.productItemEntity.toProductItem() : null
        );
    }

}
