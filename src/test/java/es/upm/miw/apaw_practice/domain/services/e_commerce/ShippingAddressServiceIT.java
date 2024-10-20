package es.upm.miw.apaw_practice.domain.services.e_commerce;
import es.upm.miw.apaw_practice.adapters.mongodb.e_commerce.ECommerceSeederService;
import es.upm.miw.apaw_practice.adapters.mongodb.e_commerce.daos.ShippingAddressRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.e_commerce.entities.ShippingAddressEntity;
import es.upm.miw.apaw_practice.domain.models.e_commerce_model.ShippingAddress;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@SpringBootTest
class ShippingAddressServiceIT {

    @Autowired
    private ShippingAddressService shippingAddressService;

    @Autowired
    private ShippingAddressRepository shippingAddressRepository;

    @Autowired
    private ECommerceSeederService seederService;

    @BeforeEach
    void seedDatabase() {
        seederService.deleteAll();
        seederService.seedDatabase();
    }

    @Test
    void testUpdateRecipientName() {
        String location = "C/Rey, 24";
        String newRecipientName = "Updated Alice";

        // Fetch the shipping address before updating
        List<ShippingAddressEntity> addressBeforeUpdate = this.shippingAddressRepository.findByLocation(location);
        Assertions.assertFalse(addressBeforeUpdate.isEmpty(), "Address not found");
        Assertions.assertEquals("Alice", addressBeforeUpdate.get(0).getRecipientName());

        // Perform the update operation
        ShippingAddress updatedAddress = this.shippingAddressService.updateRecipientName(location, newRecipientName);

        // Verify the recipient name was updated
        List<ShippingAddressEntity> addressAfterUpdate = this.shippingAddressRepository.findByLocation(location);
        Assertions.assertFalse(addressAfterUpdate.isEmpty(), "Address not found after update");
        Assertions.assertEquals(newRecipientName, updatedAddress.getRecipientName());
    }
}
