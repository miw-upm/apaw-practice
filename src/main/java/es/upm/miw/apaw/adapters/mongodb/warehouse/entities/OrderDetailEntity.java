package es.upm.miw.apaw.adapters.mongodb.warehouse.entities;

import es.upm.miw.apaw.domain.models.warehouse.OrderDetail;
import lombok.*;
import org.springframework.beans.BeanUtils;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.math.BigDecimal;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor

public class OrderDetailEntity {

    private Integer     qtyRequested;
    private Integer     qtyMoved;
    private BigDecimal  unitCost;

    @DBRef
    private ProductItemEntity productItemEntity;


    public OrderDetailEntity(OrderDetail orderDetail) {
        BeanUtils.copyProperties(orderDetail, this, "productItem");
        this.productItemEntity = orderDetail.getProductItem() == null ? null :
                new ProductItemEntity(orderDetail.getProductItem());
    }

    public OrderDetail toOrderDetail() {
        return OrderDetail.builder()
                .qtyRequested(this.qtyRequested)
                .qtyMoved(this.qtyMoved)
                .unitCost(this.unitCost)
                .productItem(this.productItemEntity != null ?
                        this.productItemEntity.toProductItem() : null)
                .build();
    }

    public static OrderDetailEntity fromOrderDetail(OrderDetail orderDetail) {
        return OrderDetailEntity.builder()
                .qtyRequested(orderDetail.getQtyRequested())
                .qtyMoved(orderDetail.getQtyMoved())
                .unitCost(orderDetail.getUnitCost())
                .productItemEntity(ProductItemEntity.fromProductItem(orderDetail.getProductItem()))
                .build();
    }

}