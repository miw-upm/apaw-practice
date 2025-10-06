package es.upm.miw.apaw.domain.persistenceports.apiary;

import es.upm.miw.apaw.domain.models.apiary.Product;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface ProductPersistence {

    Product update(Product product);
    Product updatePrice(String barcode, BigDecimal newPrice);
    Product read(String barcode);


}
