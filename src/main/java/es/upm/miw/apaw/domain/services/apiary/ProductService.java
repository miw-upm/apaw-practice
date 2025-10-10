package es.upm.miw.apaw.domain.services.apiary;

import es.upm.miw.apaw.domain.models.apiary.Product;
import es.upm.miw.apaw.domain.persistenceports.apiary.ProductPersistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class ProductService {
    private final ProductPersistence productPersistence;

    @Autowired
    public ProductService(ProductPersistence productPersistence) {
        this.productPersistence = productPersistence;
    }

    public Product update(Product product) {
        return this.productPersistence.update(product);
    }

    public Product updatePrice(String barcode, BigDecimal newPrice) {
        return this.productPersistence.updatePrice(barcode, newPrice);
    }
}
