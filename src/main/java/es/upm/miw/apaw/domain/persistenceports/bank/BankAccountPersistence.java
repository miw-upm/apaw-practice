package es.upm.miw.apaw.domain.persistenceports.bank;
import es.upm.miw.apaw.domain.models.bank.BankAccount;
import es.upm.miw.apaw.domain.models.bank.CreditCard;
import es.upm.miw.apaw.domain.models.bank.Loan;
import org.springframework.stereotype.Repository;
import java.util.UUID;
import java.util.stream.Stream;

@Repository
public interface BankAccountPersistence {
    String readStatusByAccountNumber(String accountNumber);
    void delete(String accountNumber);
    BankAccount findByAccountNumber(String accountNumber);
    Stream<Loan> applyANewLoanForABankAccount(String accountNumber, Loan loan);
    CreditCard updateCreditCard(String accountNumber, CreditCard creditCard);
    Stream<BankAccount> findByAccountHolders(UUID accountHolder);
    Stream<BankAccount> findByLoansAppliedCondition(String condition);
}
