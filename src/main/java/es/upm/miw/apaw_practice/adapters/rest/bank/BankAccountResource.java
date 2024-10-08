package es.upm.miw.apaw_practice.adapters.rest.bank;

import es.upm.miw.apaw_practice.domain.models.bank.BankAccount;
import es.upm.miw.apaw_practice.domain.services.bank.BankAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(BankAccountResource.ACCOUNTS)
public class BankAccountResource {

    static final String ID_ID = "/{id}";
    static final String ACCOUNTS = "/bank/accounts";
    static final String INTEREST = "/interest";

    private final BankAccountService bankAccountService;

    @Autowired
    public BankAccountResource(BankAccountService bankAccountService) {
        this.bankAccountService = bankAccountService;
    }

    @PutMapping(ACCOUNTS + ID_ID + INTEREST)
    public BankAccount updateHasInterest(@PathVariable String id, @RequestBody Boolean hasInterest) {
        return this.bankAccountService.updateHasInterest(id, hasInterest);
    }
}
