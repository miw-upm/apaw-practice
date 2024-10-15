package es.upm.miw.apaw_practice.adapters.mongodb.e_commerce.daos;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.adapters.mongodb.e_commerce.ECommerceSeederService;
import es.upm.miw.apaw_practice.adapters.mongodb.e_commerce.entities.ShippingAddressEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@TestConfig
public class ShippingAddressRepositoryIT {

    @Autowired
    private ShippingAddressRepository shippingAddressRepository;

    @Autowired
    private ECommerceSeederService eCommerceSeederService;

    @BeforeEach
    void setUp() {
        eCommerceSeederService.deleteAll();
        eCommerceSeederService.seedDatabase();
    }

    @Test
    void testFindByLocationNonExisting() {
        Optional<ShippingAddressEntity> shippingAddressEntity = shippingAddressRepository.findByLocation("NonExistingLocation");
        assertTrue(shippingAddressEntity.isEmpty(), "ShippingAddress should not exist for this location");
    }

    @Test
    void testFindByLocation() {
        Optional<ShippingAddressEntity> shippingAddressEntity = shippingAddressRepository.findByLocation("C/Rey, 24");
        assertTrue(shippingAddressEntity.isPresent(), "ShippingAddress should exist for this location");

        ShippingAddressEntity entity = shippingAddressEntity.get();
        assertNotNull(entity);

        assertEquals("C/Rey, 24", entity.getLocation(), "Location should match");
        assertEquals("+123456789", entity.getTelefono(), "Telefono should match");
        assertEquals("Alice", entity.getRecipientName(), "Recipient name should match");
    }
}
