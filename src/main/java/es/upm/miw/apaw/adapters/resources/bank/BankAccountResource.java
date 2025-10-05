package es.upm.miw.apaw.adapters.resources.bank;
import es.upm.miw.apaw.domain.services.bank.BankAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(BankAccountResource.BANK_ACCOUNTS)
public class BankAccountResource {
    public static final String BANK_ACCOUNTS = "/bank/bank-accounts";
    public static final String ACCOUNT_NUMBER="/{account-number}";
    public static final String STATUS="/status";
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
}
