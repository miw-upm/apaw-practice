package es.upm.miw.apaw.domain.services.apiary;

import es.upm.miw.apaw.domain.models.apiary.Product;
import es.upm.miw.apaw.domain.models.apiary.ProductPriceUpdating;
import es.upm.miw.apaw.domain.persistenceports.apiary.ProductPersistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.stream.Stream;

@Service
public class ProductService {
    private final ProductPersistence productPersistence;

    @Autowired
    public ProductService(ProductPersistence productPersistence) {
        this.productPersistence = productPersistence;
    }

    public Product updatePut(Product product) {
        return this.productPersistence.updatePut(product);
    }

    public Product updatePrice(String barcode, BigDecimal newPrice) {
        return this.productPersistence.updatePrice(barcode, newPrice);
    }

    public void updatePricesDTO(Stream<ProductPriceUpdating> productPriceUpdatingList) {
        productPriceUpdatingList.map(productNewPrice -> {
                    Product product = this.productPersistence.read(productNewPrice.getBarcode());
                    product.setPrice(productNewPrice.getPrice());
                    return product;
                })
                .forEach(product -> this.productPersistence.update(product.getBarcode(), product));
    }
}