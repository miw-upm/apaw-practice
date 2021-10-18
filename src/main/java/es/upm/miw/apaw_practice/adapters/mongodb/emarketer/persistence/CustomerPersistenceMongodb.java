package es.upm.miw.apaw_practice.adapters.mongodb.emarketer.persistence;

import es.upm.miw.apaw_practice.adapters.mongodb.emarketer.daos.CustomerRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.emarketer.entities.CustomerEntity;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.models.emarketer.Customer;
import es.upm.miw.apaw_practice.domain.persistence_ports.emarketer.CustomerPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.stream.Stream;

@Repository("customerPersistence")
public class CustomerPersistenceMongodb implements CustomerPersistence {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerPersistenceMongodb(CustomerRepository customerRepository) {this.customerRepository = customerRepository;}

    @Override
    public Stream<Customer> readAll() {
        return this.customerRepository.findAll().stream()
                .map(CustomerEntity::toCustomer);
    }

    @Override
    public Customer readByName(String name) {
        return this.customerRepository.findByName(name)
                .orElseThrow(() -> new NotFoundException("Customer with name: " + name))
                .toCustomer();
    }

    @Override
    public Customer update(String name, Customer customer) {
        CustomerEntity customerToUpdate = this.customerRepository.findByName(name)
                .orElseThrow(() -> new NotFoundException("Customer with name: " + name));
        customerToUpdate.setType(customer.getType());
        return this.customerRepository.save(customerToUpdate).toCustomer();
    }

}
