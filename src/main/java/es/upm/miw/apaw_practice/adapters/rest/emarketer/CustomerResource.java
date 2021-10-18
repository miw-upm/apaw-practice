package es.upm.miw.apaw_practice.adapters.rest.emarketer;

import es.upm.miw.apaw_practice.domain.models.emarketer.Customer;
import es.upm.miw.apaw_practice.domain.models.emarketer.Plan;
import es.upm.miw.apaw_practice.domain.services.emarketer.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Stream;

@RestController
@RequestMapping(CustomerResource.CUSTOMERS)
public class CustomerResource {

    static final String CUSTOMERS = "/emarketer/customers";
    static final String NAME = "/{name}";

    CustomerService customerService;

    @Autowired
    public CustomerResource(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public Stream<Customer> readAllCustomers() {
        return this.customerService.readAll();
    }

    @PatchMapping(CustomerResource.NAME)
    public Customer updateType(@PathVariable String name, @RequestBody Customer customer) {
        return this.customerService.updateType(name, customer);
    }

}
