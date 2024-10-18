package es.upm.miw.apaw_practice.adapters.mongodb.e_commerce.persistence;
import es.upm.miw.apaw_practice.adapters.mongodb.e_commerce.daos.ShippingAddressRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.e_commerce.entities.ShippingAddressEntity;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.models.e_commerce_model.ShippingAddress;
import es.upm.miw.apaw_practice.domain.persistence_ports.e_commerce.ShippingAddressPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository("ShippingAddressPersistence")
public class ShippingAddressPersistenceMongodb implements ShippingAddressPersistence {

    private final ShippingAddressRepository shippingAddressRepository;

    @Autowired
    public ShippingAddressPersistenceMongodb(ShippingAddressRepository shippingAddressRepository) {
        this.shippingAddressRepository = shippingAddressRepository;
    }

    @Override
    public ShippingAddress updateRecipientName(String location,String recipientName) {
        List<ShippingAddressEntity> addresses = this.shippingAddressRepository.findByLocation(location);
        if (addresses.isEmpty()) {
            throw new NotFoundException("Shipping address not found for id: " + location);
        }
        ShippingAddressEntity addressEntity = addresses.get(0);
        addressEntity.setRecipientName(recipientName);
        return this.shippingAddressRepository.save(addressEntity).toShippingAddress();
    }
}
