package es.upm.miw.apaw.adapters.mongodb.warehouse.entities;

import lombok.AllArgsConstructor;
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

public class MovementOrderEntity {

    @Id
    private UUID            idMovementOrder;

    //UserDto
    private UUID id;

    private LocalDateTime   registrationDate;
    private String          typeOrder;
    private String          partnerName;
    private String          partnerAddress;
    private Boolean         isCompleted;

}
