package es.upm.miw.apaw.domain.services.warehouse;

import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.models.warehouse.ProductItem;
import es.upm.miw.apaw.domain.persistenceports.warehouse.ProductItemPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductItemService {

    private final ProductItemPersistence productItemPersistence;

    @Autowired
    public ProductItemService(ProductItemPersistence productItemPersistence) {
        this.productItemPersistence = productItemPersistence;
    }

    public ProductItem update(String barcode, ProductItem productItem) {
        return this.productItemPersistence.update(barcode, productItem)
                .orElseThrow(() -> new NotFoundException("ProductItem barcode: " + barcode));
    }

}