package es.upm.miw.apaw_practice.adapters.rest.boardgame_cafe;

import es.upm.miw.apaw_practice.domain.models.boardgame_cafe.Customer;
import es.upm.miw.apaw_practice.domain.services.boardgame_cafe.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(CustomerResource.CUSTOMER)
public class CustomerResource {
    static final String CUSTOMER = "/boardgame-cafe/customer";

    private final CustomerService customerService;

    @Autowired
    public CustomerResource(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public Customer create(@RequestBody Customer customer) {
        return this.customerService.createCustomer(customer);
    }
}
