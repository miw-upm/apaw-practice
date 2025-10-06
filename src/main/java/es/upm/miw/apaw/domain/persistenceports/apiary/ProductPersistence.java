package es.upm.miw.apaw.domain.persistenceports.apiary;

import es.upm.miw.apaw.domain.models.apiary.Product;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductPersistence {

    Product readByBarCode(String barcode);
    Product update(Product product);

}
