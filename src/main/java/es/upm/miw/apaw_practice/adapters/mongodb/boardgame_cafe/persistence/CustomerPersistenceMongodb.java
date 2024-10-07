package es.upm.miw.apaw_practice.adapters.mongodb.boardgame_cafe.persistence;

import es.upm.miw.apaw_practice.adapters.mongodb.boardgame_cafe.daos.CustomerRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.boardgame_cafe.entities.CustomerEntity;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.models.boardgame_cafe.Customer;
import es.upm.miw.apaw_practice.domain.persistence_ports.boardgame_cafe.CustomerPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.stream.Stream;

@Repository("customerPersistence")
public class CustomerPersistenceMongodb implements CustomerPersistence {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerPersistenceMongodb (CustomerRepository customerRepository){
        this.customerRepository = customerRepository;
    }

    @Override
    public Stream<Customer> readAll() {
        return customerRepository
                .findAll()
                .stream()
                .map(CustomerEntity::toCustomer);
    }

    @Override
    public Customer create(Customer customer) {
        return customerRepository
                .save(new CustomerEntity(customer))
                .toCustomer();
    }

    @Override
    public Customer update(String email, Customer customer) {
        CustomerEntity customerEntity = customerRepository
                .findByEmail(email)
                .orElseThrow(() -> new NotFoundException("Customer email: " + email));
        customerEntity.fromCustomer(customer);
        return customerRepository
                .save(customerEntity)
                .toCustomer();
    }

    @Override
    public Customer read(String email) {
        return customerRepository
                .findByEmail(email)
                .orElseThrow(() -> new NotFoundException("Customer email: " + email))
                .toCustomer();
    }

    @Override
    public boolean existEmail(String email) {
        return customerRepository
                .findByEmail(email)
                .isPresent();
    }
}
