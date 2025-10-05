package es.upm.miw.apaw.adapters.mongodb.bank.entities;
import es.upm.miw.apaw.domain.models.UserDto;
import es.upm.miw.apaw.domain.models.bank.BankAccount;
import es.upm.miw.apaw.domain.models.bank.Loan;
import lombok.*;
import org.springframework.beans.BeanUtils;
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

    public BankAccount toBankAccount() {
        BankAccount bankAccount = new BankAccount();
        BeanUtils.copyProperties(this, bankAccount, "accountHolders", "loansApplied","creditCardAssociated");
        List<Loan> loans = this.loansApplied
                .stream()
                .map(LoanEntity::toLoan)
                .toList();
        List<UserDto> users = this.accountHolders
                .stream()
                .map(user -> UserDto.builder().id(user).build())
                .toList();
        bankAccount.setAccountHolders(users);
        bankAccount.setLoansApplied(loans);
        bankAccount.setCreditCardAssociated(this.creditCardAssociated.toCreditCard());
        return bankAccount;
    }

}
