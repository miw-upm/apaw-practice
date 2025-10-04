package es.upm.miw.apaw.domain.models.warehouse;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor

public class ProductItem {

    @NotNull
    @NotBlank
    private String barcode;

    private String appoint;

    @NotNull
    private BigDecimal cost;

    @NotBlank
    private String unitOfMeasure;

}
