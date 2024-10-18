package es.upm.miw.apaw_practice.domain.services.e_commerce;
import es.upm.miw.apaw_practice.domain.models.e_commerce_model.ProductECommerce;
import es.upm.miw.apaw_practice.domain.persistence_ports.e_commerce.ProductECommercePersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class ProductECommerceService {

    private final ProductECommercePersistence productPersistence;

    @Autowired
    public ProductECommerceService(ProductECommercePersistence productECommercePersistence) {
        this.productPersistence = productECommercePersistence;
    }

    public ProductECommerce create(ProductECommerce productECommerce) {
        return this.productPersistence.create(productECommerce);
    }
}
