package es.upm.miw.apaw_practice.adapters.mongodb.e_commerce.persistence;

import es.upm.miw.apaw_practice.adapters.mongodb.e_commerce.daos.ECommerceCustomerRepository;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.models.e_commerce_model.CustomerECommerce;
import es.upm.miw.apaw_practice.domain.persistence_ports.e_commerce.CustomerECommercePersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
@Repository("CustomerECommercePersistence")
public class CustomerECommercePersistenceMongodb implements CustomerECommercePersistence {

    private final ECommerceCustomerRepository customerRepository;

    @Autowired
    public CustomerECommercePersistenceMongodb(ECommerceCustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public CustomerECommerce findByUserName(String userName) {
        return this.customerRepository.findByUserName(userName)
                .orElseThrow(() -> new NotFoundException("User name: " + userName))
                .toCustomer();
    }
}