package es.upm.miw.apaw.domain.persistenceports.bank;
import es.upm.miw.apaw.domain.models.bank.BankAccount;
import es.upm.miw.apaw.domain.models.bank.CreditCard;
import es.upm.miw.apaw.domain.models.bank.Loan;
import org.springframework.stereotype.Repository;
import java.util.UUID;
import java.util.stream.Stream;

@Repository
public interface BankAccountPersistence {
    //GET --> BankAccount
    String readStatusByAccountNumber(String accountNumber);
    // DELETE --> BankAccount
    void delete(String accountNumber);
    BankAccount findByAccountNumber(String accountNumber);
    // POST --> Loan
    Stream<Loan> applyANewLoanForABankAccount(String accountNumber, Loan loan);
    // PUT --> CreditCard
    CreditCard updateCreditCard(String accountNumber, CreditCard creditCard);
    // Search 1
    Stream<BankAccount> findByAccountHolders(UUID accountHolder);
    // Search 2
    Stream<BankAccount> findByLoansAppliedCondition(String condition);
}
