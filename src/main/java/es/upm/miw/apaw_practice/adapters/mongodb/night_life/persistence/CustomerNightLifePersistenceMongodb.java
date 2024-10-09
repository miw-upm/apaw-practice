package es.upm.miw.apaw_practice.adapters.mongodb.night_life.persistence;

import es.upm.miw.apaw_practice.adapters.mongodb.night_life.daos.CustomerNightLifeRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.night_life.entities.CustomerEntity;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.models.night_life.Customer;
import es.upm.miw.apaw_practice.domain.persistence_ports.night_life.CustomerNightLifePersistence;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("customerNightLifePersistence")
public class CustomerNightLifePersistenceMongodb implements CustomerNightLifePersistence {
    private final CustomerNightLifeRepository customerNightLifeRepository;
    @Autowired
    public CustomerNightLifePersistenceMongodb(CustomerNightLifeRepository customerNightLifeRepository) {
        this.customerNightLifeRepository = customerNightLifeRepository;
    }

    @Override
    public Customer readByName(String name) {
        return this.customerNightLifeRepository
                .findByName(name)
                .orElseThrow(() -> new NotFoundException("Customer name:" + name))
                .toCustomer();
    }

    @Override
    public void delete(String name) {
        this.customerNightLifeRepository.deleteByName(name);
    }

    @Override
    public Customer update(String name, Customer customer) {
        CustomerEntity customerEntity =  this.customerNightLifeRepository
                .findByName(name)
                .orElseThrow(() -> new NotFoundException("Customer name:" + customer.getName()));
        BeanUtils.copyProperties(customer, customerEntity, "id");
        return this.customerNightLifeRepository.save(customerEntity).toCustomer();

    }
}
