package es.upm.miw.apaw.domain.models.warehouse;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Location {

    @NotNull
    private Integer currentStock;
    @NotNull
    @NotBlank
    private String position;

    private List<ProductItem> productItems;

    private LocalDateTime lastUpdateDate;

    @NotNull
    private Boolean avialability;

}
