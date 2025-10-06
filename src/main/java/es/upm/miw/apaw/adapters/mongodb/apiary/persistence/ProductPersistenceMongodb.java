package es.upm.miw.apaw.adapters.mongodb.apiary.persistence;

import es.upm.miw.apaw.adapters.mongodb.apiary.daos.ProductRepository;
import es.upm.miw.apaw.adapters.mongodb.apiary.entities.ProductEntity;
import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.models.apiary.Product;
import es.upm.miw.apaw.domain.persistenceports.apiary.ProductPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("productPersistence")
public class ProductPersistenceMongodb implements ProductPersistence {
    private final ProductRepository productRepository;

    @Autowired
    public ProductPersistenceMongodb(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product readByBarCode(String barcode) {
        return this.productRepository.findByBarcode(barcode)
                .orElseThrow(() -> new NotFoundException("Product barcode: " + barcode))
                .toProduct();
    }

    @Override
    public Product update(Product product) {
        ProductEntity productEntity = new ProductEntity(product);
        this.productRepository.save(productEntity);
        return product;
    }
}
