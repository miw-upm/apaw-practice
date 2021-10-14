package es.upm.miw.apaw_practice.adapters.rest.emarketer;

import es.upm.miw.apaw_practice.domain.models.emarketer.Customer;
import es.upm.miw.apaw_practice.domain.services.emarketer.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Stream;

@RestController
@RequestMapping(CustomerResource.CUSTOMERS)
public class CustomerResource {

    static final String CUSTOMERS = "/emarketer/customers";

    CustomerService customerService;

    @Autowired
    public CustomerResource(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public Stream<Customer> readAllCustomers() {
        return this.customerService.readAll();
    }

}
