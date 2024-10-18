package es.upm.miw.apaw_practice.adapters.rest.e_commerce;
import es.upm.miw.apaw_practice.domain.models.e_commerce_model.ShippingAddress;
import es.upm.miw.apaw_practice.domain.services.e_commerce.ShippingAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping(ShippingAddressResource.SHIPPING_ADDRESSES)
public class ShippingAddressResource {

    static final String LOCATION = "/{location}";
    static final String SHIPPING_ADDRESSES = "/e_commerce/shipping-addresses";
    static final String RECIPIENT_NAME = "/recipient-name";

    private final ShippingAddressService shippingAddressService;

    @Autowired
    public ShippingAddressResource(ShippingAddressService shippingAddressService) {
        this.shippingAddressService = shippingAddressService;
    }

    @PutMapping(LOCATION + RECIPIENT_NAME)
    public ShippingAddress updateRecipientName(@PathVariable String location, @RequestBody String recipientName) {
        return this.shippingAddressService.updateRecipientName(location, recipientName);
    }
}
