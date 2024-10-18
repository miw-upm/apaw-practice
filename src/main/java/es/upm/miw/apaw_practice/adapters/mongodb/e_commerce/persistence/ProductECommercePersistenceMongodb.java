package es.upm.miw.apaw_practice.adapters.mongodb.e_commerce.persistence;

import es.upm.miw.apaw_practice.adapters.mongodb.e_commerce.daos.ECommerceProductRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.e_commerce.entities.ProductECommerceEntity;
import es.upm.miw.apaw_practice.domain.models.e_commerce_model.ProductECommerce;
import es.upm.miw.apaw_practice.domain.persistence_ports.e_commerce.ProductECommercePersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
@Repository("productECommercePersistence")
public class ProductECommercePersistenceMongodb implements ProductECommercePersistence {

    private final ECommerceProductRepository productRepository;

    @Autowired
    public ProductECommercePersistenceMongodb(ECommerceProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ProductECommerce create(ProductECommerce productECommerce) {
        ProductECommerceEntity productEntity = new ProductECommerceEntity();
        productEntity.setProductName(productECommerce.getProductName());
        productEntity.setNumberProduct(productECommerce.getNumberProduct());
        productEntity.setUnitPrice(productECommerce.getUnitPrice());
        return this.productRepository.save(productEntity).toProductECommerce();
    }
}
