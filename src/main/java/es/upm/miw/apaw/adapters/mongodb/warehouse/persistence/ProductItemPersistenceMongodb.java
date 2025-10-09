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
        return this.productItemRepository.findAll().stream()
                .map(ProductItemEntity::toProductItem);
    }

    @Override
    public ProductItem read(UUID id) {
        return this.productItemRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("ProductItem id: " + id))
                .toProductItem();
    }

    @Override
    public ProductItem create(ProductItem productItem) {
        ProductItemEntity entity = new ProductItemEntity(productItem);
        this.productItemRepository.save(entity);
        return entity.toProductItem();
    }

    @Override
    public ProductItem update(UUID id, ProductItem productItem) {
        ProductItemEntity entity = this.productItemRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("ProductItem id: " + id));
        entity.fromProductItem(productItem);
        this.productItemRepository.save(entity);
        return entity.toProductItem();
    }

    @Override
    public void delete(UUID id) {
        this.productItemRepository.deleteById(id);
    }

}
