package es.upm.miw.apaw_practice.adapters.rest.bank;

import es.upm.miw.apaw_practice.domain.services.bank.InvestmentFundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(InvestmentFundResource.FUNDS)
public class InvestmentFundResource {

    static final String NAME = "/{name}";
    static final String FUNDS = "/bank/investment-funds";

    private final InvestmentFundService investmentfundService;

    @Autowired
    public InvestmentFundResource(InvestmentFundService investmentfundService) {
        this.investmentfundService = investmentfundService;
    }

    @DeleteMapping(NAME)
    public void delete(@PathVariable String name) {
        this.investmentfundService.delete(name);
    }
}
