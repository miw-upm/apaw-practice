package es.upm.miw.apaw.domain.services.bank;

import es.upm.miw.apaw.domain.models.bank.BankAccount;
import es.upm.miw.apaw.domain.models.bank.CreditCard;
import es.upm.miw.apaw.domain.models.bank.Loan;
import es.upm.miw.apaw.domain.persistenceports.bank.BankAccountPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BankAccountService {

    private final BankAccountPersistence bankAccountPersistence;

    @Autowired
    public BankAccountService(BankAccountPersistence bankAccountPersistence) {
        this.bankAccountPersistence = bankAccountPersistence;
    }

    public String readStatusByAccountNumber(String accountNumber){
        return this.bankAccountPersistence.readStatusByAccountNumber(accountNumber);
    }

    public void delete(String accountNumber){
        this.bankAccountPersistence.delete(accountNumber);
    }

    public BankAccount findByAccountNumber(String accountNumber){
        return this.bankAccountPersistence.findByAccountNumber(accountNumber);
    }

    public List<Loan> applyANewLoanForABankAccount(String accountNumber, Loan loan){
        return this.bankAccountPersistence.applyANewLoanForABankAccount(accountNumber,loan);
    }

    public CreditCard updateCreditCard(String accountNumber, CreditCard creditCard){
        return this.bankAccountPersistence.updateCreditCard(accountNumber,creditCard);
    }
}
