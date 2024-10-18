package es.upm.miw.apaw_practice.adapters.mongodb.e_commerce.persistence;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.models.e_commerce_model.ShippingAddress;
import es.upm.miw.apaw_practice.TestConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import static org.junit.jupiter.api.Assertions.*;
@TestConfig
public class ShippingAddressPersistenceMongodbIT {

    @Autowired
    private ShippingAddressPersistenceMongodb shippingAddressPersistenceMongodb;

    @Test
    void testUpdateRecipientNameNotFound() {
        assertThrows(NotFoundException.class, () ->
                this.shippingAddressPersistenceMongodb.updateRecipientName("NonExistentAddress", "New Recipient"));
    }

    @Test
    void testUpdateRecipientName() {
        ShippingAddress updatedAddress = this.shippingAddressPersistenceMongodb.updateRecipientName("C/Rey, 24", "New Recipient");
        assertNotNull(updatedAddress);
        assertEquals("C/Rey, 24", updatedAddress.getLocation());
        assertEquals("New Recipient", updatedAddress.getRecipientName());
    }
}
