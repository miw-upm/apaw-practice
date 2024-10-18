package es.upm.miw.apaw_practice.domain.persistence_ports.e_commerce;
import es.upm.miw.apaw_practice.domain.models.e_commerce_model.ProductECommerce;
import org.springframework.stereotype.Repository;
@Repository
public interface ProductECommercePersistence {
    ProductECommerce create(ProductECommerce productECommerce);
}
