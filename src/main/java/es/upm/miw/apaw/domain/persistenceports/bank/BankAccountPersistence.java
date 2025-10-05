package es.upm.miw.apaw.domain.persistenceports.bank;
import es.upm.miw.apaw.domain.models.bank.BankAccount;
import es.upm.miw.apaw.domain.models.bank.Loan;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface BankAccountPersistence {
    String readStatusByAccountNumber(String accountNumber);
    void delete(String accountNumber);
    BankAccount findByAccountNumber(String accountNumber);
    List<Loan> applyANewLoanForABankAccount(String accountNumber, Loan loan);
}
