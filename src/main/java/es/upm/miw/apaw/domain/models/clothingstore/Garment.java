package es.upm.miw.apaw.domain.models.clothingstore;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Garment {

    @NotNull
    private UUID garmentId;

    private String size;

    @NotNull
    private BigDecimal price;

    private Boolean onSale;
}
