package es.upm.miw.apaw.adapters.mongodb.bank.persistence;
import es.upm.miw.apaw.adapters.mongodb.bank.daos.BankAccountRepository;
import es.upm.miw.apaw.adapters.mongodb.bank.entities.BankAccountEntity;
import es.upm.miw.apaw.adapters.mongodb.bank.entities.CreditCardEntity;
import es.upm.miw.apaw.adapters.mongodb.bank.entities.LoanEntity;
import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.models.bank.BankAccount;
import es.upm.miw.apaw.domain.models.bank.CreditCard;
import es.upm.miw.apaw.domain.models.bank.Loan;
import es.upm.miw.apaw.domain.persistenceports.bank.BankAccountPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;

@Repository("bankAccountPersistence")
public class BankAccountPersistenceMongodb implements BankAccountPersistence {

    private final BankAccountRepository bankAccountRepository;
    private static final String BANK_ACCOUNT_ERROR_MESSAGE="Bank account number: ";

    @Autowired
    public BankAccountPersistenceMongodb(BankAccountRepository bankAccountRepository) {
        this.bankAccountRepository = bankAccountRepository;
    }


    @Override
    public String readStatusByAccountNumber(String accountNumber) {
        return this.bankAccountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new NotFoundException(BANK_ACCOUNT_ERROR_MESSAGE + accountNumber))
                .getStatus();
    }

    @Override
    public void delete(String accountNumber) {
        this.bankAccountRepository.deleteByAccountNumber(accountNumber);
    }

    public BankAccount findByAccountNumber(String accountNumber){
        return this.bankAccountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new NotFoundException(BANK_ACCOUNT_ERROR_MESSAGE + accountNumber))
                .toBankAccount();
    }

    @Override
    public List<Loan> applyANewLoanForABankAccount(String accountNumber, Loan loan) {
        BankAccountEntity bankAccount = this.bankAccountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new NotFoundException(BANK_ACCOUNT_ERROR_MESSAGE + accountNumber));
        if (bankAccount.getLoansApplied()==null){
            bankAccount.setLoansApplied(new ArrayList<>());
        }
        bankAccount.getLoansApplied().add(new LoanEntity(loan));

        return this.bankAccountRepository.save(bankAccount).toBankAccount().getLoansApplied();
    }

    @Override
    public CreditCard updateCreditCard(String accountNumber,CreditCard creditCard) {

        BankAccountEntity bankAccount = this.bankAccountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(()-> new NotFoundException(BANK_ACCOUNT_ERROR_MESSAGE + accountNumber));

        CreditCardEntity cardAssociated = bankAccount.getCreditCardAssociated();
        if(cardAssociated==null || !creditCard.getCardNumber().equals(cardAssociated.getCardNumber())){
            throw new NotFoundException("Credit Card number: "+creditCard.getCardNumber());
        }
        bankAccount.setCreditCardAssociated(new CreditCardEntity(creditCard));
        return this.bankAccountRepository.save(bankAccount).toBankAccount().getCreditCardAssociated();
    }
}
