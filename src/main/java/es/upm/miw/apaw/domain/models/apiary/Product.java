package es.upm.miw.apaw.domain.models.apiary;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @NotNull
    @NotBlank
    private String barcode;
    @NotNull
    @NotBlank
    private String product;
    @NotNull
    private BigDecimal price;
    @NotNull
    @NotEmpty
    private List<Sale> sales;
}
