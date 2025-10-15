package es.upm.miw.apaw.domain.persistenceports.warehouse;

import es.upm.miw.apaw.domain.models.warehouse.ProductItem;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductItemPersistence {

    Optional<ProductItem> readByBarcode(String barcode);
    Optional<ProductItem> update(String barcode, ProductItem productItem);

}