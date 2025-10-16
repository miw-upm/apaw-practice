package es.upm.miw.apaw.domain.models.apiary;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductPriceUpdating {
    @NotNull
    @NotBlank
    private String barcode;
    @NotNull
    private BigDecimal price;
}