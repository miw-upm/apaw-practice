package es.upm.miw.apaw.adapters.mongodb.warehouse.entities;

import es.upm.miw.apaw.domain.models.warehouse.OrderDetail;
import es.upm.miw.apaw.domain.models.warehouse.ProductItem;
import lombok.*;
import org.springframework.beans.BeanUtils;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Document

public class OrderDetailEntity {

    @DBRef
    private ProductItemEntity productItemEntity;
    private Integer     qtyRequested;
    private Integer     qtyMoved;
    private BigDecimal  unitCost;

    public OrderDetailEntity(OrderDetail orderDetail) {
        BeanUtils.copyProperties(orderDetail, this);
    }

    public OrderDetail toOrderDetail() {
        return new OrderDetail(
                this.productItemEntity.toProductItem(),
                this.qtyRequested,
                this.qtyMoved,
                this.unitCost
        );
    }

}
