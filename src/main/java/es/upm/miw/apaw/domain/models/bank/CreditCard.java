package es.upm.miw.apaw.domain.models.bank;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreditCard {

    @NotBlank
    private String cardNumber;
    @NotNull
    private LocalDate cardExpirationDate;
    @NotNull
    private Integer cvv;
    @NotNull
    private BigDecimal cardLimit;
    @NotNull
    private BankAccount linkedAccount;

}
