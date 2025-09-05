package es.upm.miw.apaw.adapters.mongodb.shop.daos;

import es.upm.miw.apaw.adapters.mongodb.shop.entities.ArticleEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ArticleRepository extends MongoRepository<ArticleEntity, UUID> {
    Optional<ArticleEntity> findByBarcode(String barcode);

    List<ArticleEntity> findByProviderAndPriceGreaterThan(String provider, BigDecimal price);
}
