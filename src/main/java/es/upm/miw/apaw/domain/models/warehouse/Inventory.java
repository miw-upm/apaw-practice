package es.upm.miw.apaw.domain.models.warehouse;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Inventory {

    @NotNull
    private UUID idInventory;

    @NotNull
    private UUID idProductItem;

    @NotNull
    private Integer currentStock;

    private String location;

    @NotNull
    private LocalDateTime lastUpdateDate;

}
