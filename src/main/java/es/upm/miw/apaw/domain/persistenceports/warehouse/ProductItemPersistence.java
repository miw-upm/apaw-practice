package es.upm.miw.apaw.domain.persistenceports.warehouse;

import es.upm.miw.apaw.domain.models.warehouse.ProductItem;
import org.springframework.stereotype.Repository;

import java.util.UUID;
import java.util.stream.Stream;

@Repository
public interface ProductItemPersistence {

    Stream<ProductItem> readAll();
    ProductItem read(UUID id);
    ProductItem create(ProductItem productItem);
    ProductItem update(UUID id, ProductItem productItem);
    void delete(UUID id);

}
