package es.upm.miw.apaw.adapters.mongodb.warehouse.entities;

import es.upm.miw.apaw.domain.models.UserDto;
import es.upm.miw.apaw.domain.models.warehouse.MovementOrder;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.*;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;
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
        BeanUtils.copyProperties(movementOrder, this, "orderDetails", "user");
        this.orderDetailEntities = movementOrder.getOrderDetails() == null ? null :
                movementOrder.getOrderDetails().stream()
                        .map(OrderDetailEntity::new)
                        .toList();
        this.userId = movementOrder.getUser() != null ? movementOrder.getUser().getId() : null;
    }

    public MovementOrder toMovementOrder() {
        return MovementOrder.builder()
                .id(this.id)
                .registrationDate(this.registrationDate)
                .typeOrder(this.typeOrder)
                .partnerName(this.partnerName)
                .partnerAddress(this.partnerAddress)
                .completedOrder(this.completedOrder)
                .orderDetails(this.orderDetailEntities == null ? null :
                        this.orderDetailEntities.stream()
                                .map(OrderDetailEntity::toOrderDetail)
                                .toList())
                .user(this.userId != null ? UserDto.builder().id(this.userId).build() : null)
                .build();
    }

}
