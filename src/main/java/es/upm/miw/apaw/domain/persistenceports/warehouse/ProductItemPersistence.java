package es.upm.miw.apaw.domain.persistenceports.warehouse;

import es.upm.miw.apaw.domain.models.warehouse.ProductItem;
import org.springframework.stereotype.Repository;

import java.util.stream.Stream;

@Repository
public interface ProductItemPersistence {

    Stream<ProductItem> readAll();
    ProductItem create(ProductItem productItem);
    ProductItem update(String barcode, ProductItem productItem);
    ProductItem read(String barcode);

}
