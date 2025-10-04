package es.upm.miw.apaw.adapters.mongodb.bank.entities;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Document
public class BankAccountEntity {

    @Id
    private UUID id;
    @EqualsAndHashCode.Include
    @Indexed(unique = true)
    private String accountNumber;
    private String status;
    private BigDecimal balance;
    private CreditCardEntity creditCardAssociated;
    private List<LoanEntity> loansApplied;
    private List<UUID> accountHolders;

}
