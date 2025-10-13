package es.upm.miw.apaw.adapters.mongodb.warehouse.persistence;

import es.upm.miw.apaw.adapters.mongodb.warehouse.daos.ProductItemRepository;
import es.upm.miw.apaw.adapters.mongodb.warehouse.entities.ProductItemEntity;
import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.models.warehouse.ProductItem;
import es.upm.miw.apaw.domain.persistenceports.warehouse.ProductItemPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.UUID;
import java.util.stream.Stream;

@Repository("productItemPersistence")
public class ProductItemPersistenceMongodb implements ProductItemPersistence {

    private final ProductItemRepository productItemRepository;

    @Autowired
    public ProductItemPersistenceMongodb(ProductItemRepository productItemRepository) {
        this.productItemRepository = productItemRepository;
    }

    @Override
    public Stream<ProductItem> readAll() {
        return this.productItemRepository
                .findAll().stream()
                .map(ProductItemEntity::toProductItem);
    }

    @Override
    public ProductItem create(ProductItem productItem) {
        return this.productItemRepository
                .save(new ProductItemEntity(productItem))
                .toProductItem();
    }

    @Override
    public ProductItem update(String barcode, ProductItem productItem) {
        ProductItemEntity productItemEntity = this.productItemRepository
                .findByBarcode(barcode)
                .orElseThrow(() -> new NotFoundException("ProductItem barcode: " + barcode));
        productItemEntity.fromProductItem(productItem);
        return this.productItemRepository
                .save(productItemEntity)
                .toProductItem();
    }

    @Override
    public ProductItem read(String barcode) {
        return this.productItemRepository
                .findByBarcode(barcode)
                .orElseThrow(() -> new NotFoundException("ProductItem not found: " + barcode))
                .toProductItem();
    }

}
