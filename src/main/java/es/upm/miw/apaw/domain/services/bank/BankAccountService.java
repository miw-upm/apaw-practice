package es.upm.miw.apaw.domain.services.bank;

import es.upm.miw.apaw.domain.models.UserDto;
import es.upm.miw.apaw.domain.models.bank.BankAccount;
import es.upm.miw.apaw.domain.models.bank.CreditCard;
import es.upm.miw.apaw.domain.models.bank.Loan;
import es.upm.miw.apaw.domain.models.bank.PaymentHistory;
import es.upm.miw.apaw.domain.persistenceports.bank.BankAccountPersistence;
import es.upm.miw.apaw.domain.restclients.UserRestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Stream;

@Service
public class BankAccountService {

    private final BankAccountPersistence bankAccountPersistence;
    private final UserRestClient userRestClient;

    @Autowired
    public BankAccountService(BankAccountPersistence bankAccountPersistence, UserRestClient userRestClient) {
        this.bankAccountPersistence = bankAccountPersistence;
        this.userRestClient = userRestClient;
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

    public Stream<Loan> applyANewLoanForABankAccount(String accountNumber, Loan loan){
        return this.bankAccountPersistence.applyANewLoanForABankAccount(accountNumber,loan);
    }

    public CreditCard updateCreditCard(String accountNumber, CreditCard creditCard){
        return this.bankAccountPersistence.updateCreditCard(accountNumber,creditCard);
    }

    public BigDecimal obtainTotalQuantityByMobile(String mobile){
        UserDto user = this.userRestClient.readByMobile(mobile);
        return this.bankAccountPersistence.findByAccountHolders(user.getId())
                .flatMap(bankAccount -> Optional.ofNullable(bankAccount.getLoansApplied())
                        .orElse(List.of())
                        .stream())
                .map(Loan::getQuantity)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public Stream<UUID> obtainPaidPaymentHistoryIdByCondition(String condition){

        return this.bankAccountPersistence.findByLoansAppliedCondition(condition)
                .map(BankAccount::getCreditCardAssociated)
                .filter(Objects::nonNull)
                .map(CreditCard::getPaymentHistoryList)
                .filter(Objects::nonNull)
                .flatMap(paymentHistories -> paymentHistories.stream()
                        .filter(PaymentHistory::getPaid)
                        .map(PaymentHistory::getId))
                .distinct();
    }
}
