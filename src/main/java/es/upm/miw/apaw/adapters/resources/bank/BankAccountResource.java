package es.upm.miw.apaw.adapters.resources.bank;
import es.upm.miw.apaw.domain.models.bank.CreditCard;
import es.upm.miw.apaw.domain.models.bank.Loan;
import es.upm.miw.apaw.domain.services.bank.BankAccountService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;
import java.util.UUID;
import java.util.stream.Stream;

@RestController
@RequestMapping(BankAccountResource.BANK_ACCOUNTS)
public class BankAccountResource {
    public static final String BANK_ACCOUNTS = "/bank/bank-accounts";
    public static final String ACCOUNT_NUMBER="/{account-number}";
    public static final String STATUS="/status";
    public static final String LOANS="/loans";
    public static final String MOBILE="/{mobile}";
    public static final String TOTAL_QUANTITIES ="/total-quantities";
    public static final String CREDIT_CARDS="/credit-cards";
    public static final String PAYMENT_HISTORIES="/payment-histories";
    public static final String ID="/id";
    private final BankAccountService bankAccountService;


    @Autowired
    public BankAccountResource(BankAccountService bankAccountService) {
        this.bankAccountService = bankAccountService;
    }

    @GetMapping(ACCOUNT_NUMBER+STATUS)
    public String readStatusByAccountNumber(@PathVariable("account-number") String accountNumber) {
        return this.bankAccountService.readStatusByAccountNumber(accountNumber);
    }

    @DeleteMapping(ACCOUNT_NUMBER)
    public void delete(@PathVariable("account-number") String accountNumber){
        this.bankAccountService.delete(accountNumber);
    }

    @PostMapping(ACCOUNT_NUMBER+LOANS)
    public Stream<Loan> applyANewLoanForABankAccount(@PathVariable("account-number") String accountNumber, @Valid @RequestBody Loan newLoan){
        return this.bankAccountService.applyANewLoanForABankAccount(accountNumber,newLoan);
    }

    @PutMapping(ACCOUNT_NUMBER+CREDIT_CARDS)
    public CreditCard updateCreditCard(@PathVariable("account-number") String accountNumber, @RequestBody CreditCard creditCard){
        return this.bankAccountService.updateCreditCard(accountNumber,creditCard);
    }

    @GetMapping(MOBILE+LOANS+TOTAL_QUANTITIES)
    public BigDecimal obtainTotalQuantityByMobile(@PathVariable("mobile") String mobile){
        return this.bankAccountService.obtainTotalQuantityByMobile(mobile);
    }

    @GetMapping(CREDIT_CARDS+PAYMENT_HISTORIES+ID)
    public Stream<UUID> obtainPaidPaymentHistoriesByCondition(@RequestParam String condition, @RequestParam(defaultValue = "true") boolean paid){
        return this.bankAccountService.obtainPaidPaymentHistoryIdByCondition(condition);
    }
}
