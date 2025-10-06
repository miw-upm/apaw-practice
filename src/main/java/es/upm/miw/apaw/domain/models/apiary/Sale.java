package es.upm.miw.apaw.domain.models.apiary;

import es.upm.miw.apaw.domain.models.UserDto;

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

public class Sale {
    @NotNull
    @NotBlank
    private Integer idSale;
    private Integer paymentForm;
    private String shippingAddress;
    private BigDecimal amount;
    @NotNull
    private UserDto client;
}