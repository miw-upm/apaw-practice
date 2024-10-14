package es.upm.miw.apaw_practice.adapters.rest.bank;

import es.upm.miw.apaw_practice.domain.models.bank.BankAccount;
import es.upm.miw.apaw_practice.domain.services.bank.BankAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(BankAccountResource.ACCOUNTS)
public class BankAccountResource {

    static final String IBAN = "/{iban}";
    static final String ACCOUNTS = "/bank/accounts";

    private final BankAccountService bankAccountService;

    @Autowired
    public BankAccountResource(BankAccountService ) { 
        this.bankAccountService = bankAccountService;
    }
    @PutMapping(IBAN)
    public BankAccount update(@PathVariable String iban, @RequestBody BankAccount bankAccount) {
        return this.bankAccountService.update(iban, bankAccount);
    }

    @GetMapping(IBAN)
    public List<String> getInvestmentFundNames(@PathVariable String iban) {
        return this.bankAccountService.getInvestmentFundNames(iban);
    }
}
