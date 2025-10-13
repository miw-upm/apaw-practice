package es.upm.miw.apaw.adapters.mongodb.warehouse.entities;

import es.upm.miw.apaw.domain.models.UserDto;
import es.upm.miw.apaw.domain.models.warehouse.MovementOrder;
import es.upm.miw.apaw.domain.models.warehouse.OrderDetail;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.*;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Document

public class MovementOrderEntity {

    @Id
    @EqualsAndHashCode.Include
    private UUID id;

    @NotNull
    private LocalDateTime registrationDate;

    @NotBlank
    private String typeOrder;

    private String partnerName;
    private String partnerAddress;
    private Boolean completedOrder;

    private List<OrderDetailEntity> orderDetailEntities;

    private UUID userId;

    public MovementOrderEntity(MovementOrder movementOrder) {
        BeanUtils.copyProperties(movementOrder, this, "user", "orderDetails");
        this.userId = movementOrder.getUser().getId();
        this.orderDetailEntities = movementOrder.getOrderDetails().stream()
                .map(OrderDetailEntity::new)
                .toList();
    }

    public MovementOrder toMovementOrder() {
        MovementOrder movementOrder = new MovementOrder();
        BeanUtils.copyProperties(this, movementOrder, "user", "orderDetailEntities");
        movementOrder.setUser(UserDto.builder().id(this.userId).build());
        List<OrderDetail> orderDetails = this.orderDetailEntities.stream()
                .map(OrderDetailEntity::toOrderDetail)
                .toList();
        movementOrder.setOrderDetails(orderDetails);
        return movementOrder;
    }

}
