package es.upm.miw.apaw_practice.domain.services.e_commerce;
import es.upm.miw.apaw_practice.domain.models.e_commerce_model.ShippingAddress;
import es.upm.miw.apaw_practice.domain.persistence_ports.e_commerce.ShippingAddressPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class ShippingAddressService {
    private final ShippingAddressPersistence shippingAddressPersistence;

    @Autowired
    public ShippingAddressService(ShippingAddressPersistence shippingAddressPersistence) {
        this.shippingAddressPersistence = shippingAddressPersistence;
    }

    public ShippingAddress updateRecipientName(String location, String recipientName) {
        return this.shippingAddressPersistence.updateRecipientName(location, recipientName);
    }
}
