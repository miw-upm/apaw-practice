package es.upm.miw.apaw_practice.adapters.mongodb.e_commerce.daos;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.adapters.mongodb.e_commerce.entities.ShippingAddressEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

@TestConfig
class ShippingAddressRepositoryIT {

    @Autowired
    private ShippingAddressRepository shippingAddressRepository;

    @Test
    void testFindByLocation() {
        List<ShippingAddressEntity> shippingAddresses = this.shippingAddressRepository.findByLocation("C/Rey, 24");
        assertFalse(shippingAddresses.isEmpty());
        ShippingAddressEntity shippingAddress = shippingAddresses.get(0);
        assertEquals("C/Rey, 24", shippingAddress.getLocation());
        assertEquals("Alice", shippingAddress.getRecipientName());
    }

    @Test
    void testFindAll() {
        List<ShippingAddressEntity> shippingAddresses = this.shippingAddressRepository.findAll();
        assertNotNull(shippingAddresses);
        assertTrue(shippingAddresses.size() > 0);
    }

    @Test
    void testSaveShippingAddress() {
        ShippingAddressEntity shippingAddressEntity = new ShippingAddressEntity("C/Fake, 100", "+667249920", "YiNan");
        ShippingAddressEntity savedShippingAddress = this.shippingAddressRepository.save(shippingAddressEntity);
        assertNotNull(savedShippingAddress);
        assertEquals("C/Fake, 100", savedShippingAddress.getLocation());
        assertEquals("YiNan", savedShippingAddress.getRecipientName());
    }
}
