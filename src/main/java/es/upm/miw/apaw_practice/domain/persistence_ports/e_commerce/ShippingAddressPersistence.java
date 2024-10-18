package es.upm.miw.apaw_practice.domain.persistence_ports.e_commerce;
import es.upm.miw.apaw_practice.domain.models.e_commerce_model.ShippingAddress;
import org.springframework.stereotype.Repository;
@Repository
public interface ShippingAddressPersistence {
    ShippingAddress updateRecipientName(String location, String recipientName);
}
