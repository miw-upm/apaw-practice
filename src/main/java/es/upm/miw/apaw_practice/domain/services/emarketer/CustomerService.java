package es.upm.miw.apaw_practice.domain.services.emarketer;

import es.upm.miw.apaw_practice.domain.models.emarketer.Customer;
import es.upm.miw.apaw_practice.domain.persistence_ports.emarketer.CustomerPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Stream;

@Service
public class CustomerService {
    private final CustomerPersistence customerPersistence;

    @Autowired
    public CustomerService(CustomerPersistence customerPersistence) {
        this.customerPersistence = customerPersistence;
    }

    public Stream<Customer> readAll() {
        return this.customerPersistence.readAll();
    }

    public Customer updateType(String name, Customer customer) {
        Customer customerToUpdate = this.customerPersistence.readByName(name);
        return this.customerPersistence.update(customerToUpdate.getName(), customer);
    }
}
