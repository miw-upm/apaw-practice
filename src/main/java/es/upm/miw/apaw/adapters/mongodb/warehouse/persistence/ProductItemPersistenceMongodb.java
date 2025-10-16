package es.upm.miw.apaw.adapters.mongodb.warehouse.persistence;

import es.upm.miw.apaw.adapters.mongodb.warehouse.daos.ProductItemRepository;
import es.upm.miw.apaw.adapters.mongodb.warehouse.entities.ProductItemEntity;
import es.upm.miw.apaw.domain.models.warehouse.ProductItem;
import es.upm.miw.apaw.domain.persistenceports.warehouse.ProductItemPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("productItemPersistence")
public class ProductItemPersistenceMongodb implements ProductItemPersistence {

    private final ProductItemRepository productItemRepository;

    @Autowired
    public ProductItemPersistenceMongodb(ProductItemRepository productItemRepository) {
        this.productItemRepository = productItemRepository;
    }

    @Override
    public Optional<ProductItem> readByBarcode(String barcode) {
        return this.productItemRepository.findByBarcode(barcode)
                .map(ProductItemEntity::toProductItem);
    }

    @Override
    public Optional<ProductItem> update(String barcode, ProductItem productItem) {
        return this.productItemRepository.findByBarcode(barcode)
                .map(entity -> {
                    entity.setAppoint(productItem.getAppoint());
                    entity.setCost(productItem.getCost());
                    entity.setUnitOfMeasure(productItem.getUnitOfMeasure());
                    ProductItemEntity updated = this.productItemRepository.save(entity);
                    return updated.toProductItem();
                });
    }

}