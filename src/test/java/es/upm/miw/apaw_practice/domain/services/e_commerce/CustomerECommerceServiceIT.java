package es.upm.miw.apaw_practice.domain.services.e_commerce;
import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.adapters.mongodb.e_commerce.ECommerceSeederService;
import es.upm.miw.apaw_practice.adapters.mongodb.e_commerce.daos.ECommerceCustomerRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.e_commerce.entities.CustomerECommerceEntity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Stream;
@TestConfig
public class CustomerECommerceServiceIT {

    private final List<String> expectedCustomerUsernames = List.of("user1", "user2", "user3", "user4");

    @Autowired
    private ECommerceSeederService eCommerceSeederService;

    @Autowired
    private ECommerceCustomerRepository eCommerceCustomerRepository;

    @Test
    void testFindByUserName() {
        eCommerceSeederService.seedDatabase();
        Stream<CustomerECommerceEntity> customerStream = eCommerceCustomerRepository.findAll().stream();
        List<CustomerECommerceEntity> customers = customerStream.toList();
        Assertions.assertFalse(customers.isEmpty());
        customers.forEach(customer -> {
            Assertions.assertTrue(expectedCustomerUsernames.contains(customer.getUserName()));
        });

    }
}
