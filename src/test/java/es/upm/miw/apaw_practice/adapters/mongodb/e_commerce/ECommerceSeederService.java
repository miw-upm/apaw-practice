package es.upm.miw.apaw_practice.adapters.mongodb.e_commerce;

import es.upm.miw.apaw_practice.adapters.mongodb.e_commerce.daos.*;
import es.upm.miw.apaw_practice.adapters.mongodb.e_commerce.entities.CustomerECommerceEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.e_commerce.entities.ProductECommerceEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.e_commerce.entities.ShippingAddressEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.e_commerce.entities.ShoppingCartECommerceEntity;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class ECommerceSeederService {

    @Autowired
    private ECommerceCustomerRepository eCommerceCustomerRepository;

    @Autowired
    private ECommerceProductRepository productRepository;

    @Autowired
    private ShippingAddressRepository shippingAddressRepository;

    @Autowired
    private ECommerceShoppingCartRepository eCommerceShoppingCartRepository;

    public void seedDatabase() {
        LogManager.getLogger(this.getClass()).warn("------- E-Commerce Initial Load -----------");

        ProductECommerceEntity[] products = {
                new ProductECommerceEntity("Laptop", 10, BigDecimal.valueOf(1200.00)),
                new ProductECommerceEntity("Smartphone", 50, BigDecimal.valueOf(800.00)),
                new ProductECommerceEntity("Tablet", 20, BigDecimal.valueOf(600.00)),
                new ProductECommerceEntity("Headphones",  100, BigDecimal.valueOf(150.00))
        };
        List<ProductECommerceEntity> savedProducts = this.productRepository.saveAll(Arrays.asList(products));

        ShippingAddressEntity[] shippingAddresses = {
                new ShippingAddressEntity("C/Rey, 24", "+123456789", "Alice"),
                new ShippingAddressEntity("C/Gamepolis, 2", "+987654321", "Bob"),
                new ShippingAddressEntity("C/Princesa, 65", "+111222333", "Charlie"),
                new ShippingAddressEntity("C/Alcalá, 12", "+444555666", "Dave")
        };
        List<ShippingAddressEntity> savedShippingAddresses = this.shippingAddressRepository.saveAll(Arrays.asList(shippingAddresses));

        ShoppingCartECommerceEntity[] shoppingCarts = {
                new ShoppingCartECommerceEntity(1, LocalDateTime.now(), false, BigDecimal.valueOf(1800.00), Arrays.asList(savedProducts.get(0), savedProducts.get(1))),
                new ShoppingCartECommerceEntity(2, LocalDateTime.now(), true, BigDecimal.valueOf(800.00), Collections.singletonList(savedProducts.get(1))),
                new ShoppingCartECommerceEntity(3, LocalDateTime.now(), false, BigDecimal.valueOf(750.00), Arrays.asList(savedProducts.get(3), savedProducts.get(2))),
                new ShoppingCartECommerceEntity(4, LocalDateTime.now(), true, BigDecimal.valueOf(150.00), Collections.singletonList(savedProducts.get(3)))
        };
        List<ShoppingCartECommerceEntity> savedShoppingCarts = this.eCommerceShoppingCartRepository.saveAll(Arrays.asList(shoppingCarts));

        CustomerECommerceEntity[] customers = {
                new CustomerECommerceEntity("user1", "user1@example.com", 12345, savedShoppingCarts.get(0), Arrays.asList(savedShippingAddresses.get(0), savedShippingAddresses.get(1))),
                new CustomerECommerceEntity( "user2", "user2@example.com", 54321, savedShoppingCarts.get(1), Collections.singletonList(savedShippingAddresses.get(2))),
                new CustomerECommerceEntity( "user3", "user3@example.com", 11111, savedShoppingCarts.get(2), Collections.singletonList(savedShippingAddresses.get(3))),
                new CustomerECommerceEntity("user4", "user4@example.com", 22222, savedShoppingCarts.get(3), Arrays.asList(savedShippingAddresses.get(1), savedShippingAddresses.get(3)))
        };
        this.eCommerceCustomerRepository.saveAll(Arrays.asList(customers));
    }

    public void deleteAll() {
        this.eCommerceCustomerRepository.deleteAll();
        this.productRepository.deleteAll();
        this.shippingAddressRepository.deleteAll();
        this.eCommerceShoppingCartRepository.deleteAll();
    }
}
