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
    private UUID idProductItem;

    @NotBlank
    private String barcodeProductItem;

    @NotBlank
    private String nameProductItem;

    @NotNull
    private BigDecimal priceProductItem;

    @NotBlank
    private String unitOfMeasure;

}
