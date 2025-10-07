package es.upm.miw.apaw.domain.persistenceports.bank;
import es.upm.miw.apaw.domain.models.bank.BankAccount;
import es.upm.miw.apaw.domain.models.bank.CreditCard;
import es.upm.miw.apaw.domain.models.bank.Loan;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface BankAccountPersistence {
    //GET --> BankAccount
    String readStatusByAccountNumber(String accountNumber);
    // DELETE --> BankAccount
    void delete(String accountNumber);
    BankAccount findByAccountNumber(String accountNumber);
    // POST --> Loan
    List<Loan> applyANewLoanForABankAccount(String accountNumber, Loan loan);
    // PUT --> CreditCard
    CreditCard updateCreditCard(String accountNumber, CreditCard creditCard);
}
