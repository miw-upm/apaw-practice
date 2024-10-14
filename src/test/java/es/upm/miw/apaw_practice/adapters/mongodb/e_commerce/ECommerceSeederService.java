package es.upm.miw.apaw_practice.adapters.mongodb.e_commerce;

import es.upm.miw.apaw_practice.adapters.mongodb.e_commerce.daos.ECommerceCustomerRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.e_commerce.daos.ECommerceShoppingCartRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.e_commerce.daos.ProductRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.e_commerce.daos.ShippingAddressRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.e_commerce.entities.CustomerEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.e_commerce.entities.ProductEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.e_commerce.entities.ShippingAddressEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.e_commerce.entities.ShoppingCartEntity;
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
    private ProductRepository productRepository;

    @Autowired
    private ShippingAddressRepository shippingAddressRepository;

    @Autowired
    private ECommerceShoppingCartRepository eCommerceShoppingCartRepository;

    public void seedDatabase() {
        LogManager.getLogger(this.getClass()).warn("------- E-Commerce Initial Load -----------");

        ProductEntity[] products = {
                new ProductEntity("product1", "Laptop", 10, BigDecimal.valueOf(1200.00)),
                new ProductEntity("product2", "Smartphone", 50, BigDecimal.valueOf(800.00)),
                new ProductEntity("product3", "Tablet", 20, BigDecimal.valueOf(600.00)),
                new ProductEntity("product4", "Headphones", 100, BigDecimal.valueOf(150.00))
        };
        List<ProductEntity> savedProducts = this.productRepository.saveAll(Arrays.asList(products));

        ShippingAddressEntity[] shippingAddresses = {
                new ShippingAddressEntity("C/Rey, 24", "+123456789", "Alice"),
                new ShippingAddressEntity("C/Gamepolis, 2", "+987654321", "Bob"),
                new ShippingAddressEntity("C/Princesa, 65", "+111222333", "Charlie"),
                new ShippingAddressEntity("C/Alcalá, 12", "+444555666", "Dave")
        };
        List<ShippingAddressEntity> savedShippingAddresses = this.shippingAddressRepository.saveAll(Arrays.asList(shippingAddresses));

        ShoppingCartEntity[] shoppingCarts = {
                new ShoppingCartEntity(1, LocalDateTime.now(), false, BigDecimal.valueOf(1800.00), Arrays.asList(savedProducts.get(0), savedProducts.get(1))),
                new ShoppingCartEntity(2, LocalDateTime.now(), true, BigDecimal.valueOf(800.00), Collections.singletonList(savedProducts.get(1))),
                new ShoppingCartEntity(3, LocalDateTime.now(), false, BigDecimal.valueOf(750.00), Arrays.asList(savedProducts.get(3), savedProducts.get(2))),
                new ShoppingCartEntity(4, LocalDateTime.now(), true, BigDecimal.valueOf(150.00), Collections.singletonList(savedProducts.get(3)))
        };
        List<ShoppingCartEntity> savedShoppingCarts = this.eCommerceShoppingCartRepository.saveAll(Arrays.asList(shoppingCarts));

        CustomerEntity[] customers = {
                new CustomerEntity("1", "user1", "user1@example.com", 12345, savedShoppingCarts.get(0), Arrays.asList(savedShippingAddresses.get(0), savedShippingAddresses.get(1))),
                new CustomerEntity("2", "user2", "user2@example.com", 54321, savedShoppingCarts.get(1), Collections.singletonList(savedShippingAddresses.get(2))),
                new CustomerEntity("3", "user3", "user3@example.com", 11111, savedShoppingCarts.get(2), Collections.singletonList(savedShippingAddresses.get(3))),
                new CustomerEntity("4", "user4", "user4@example.com", 22222, savedShoppingCarts.get(3), Arrays.asList(savedShippingAddresses.get(1), savedShippingAddresses.get(3)))
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