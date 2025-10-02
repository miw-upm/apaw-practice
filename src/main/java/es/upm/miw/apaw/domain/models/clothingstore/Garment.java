package es.upm.miw.apaw.domain.models.clothingstore;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Garment {
    @NotNull
    private UUID garmentId;

    @NotBlank
    private String size;

    @NotNull
    private BigDecimal price;

    @NotNull
    private Boolean onSale;
}