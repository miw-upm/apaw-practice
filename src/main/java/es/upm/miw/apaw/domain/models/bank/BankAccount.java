package es.upm.miw.apaw.domain.models.bank;
import es.upm.miw.apaw.domain.models.UserDto;
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
public class BankAccount {

    @NotBlank
    private String accountNumber;
    @NotBlank
    private String accountStatus;
    @NotEmpty
    private List<UserDto> accountHolders;
    @NotNull
    private BigDecimal balance;
}
