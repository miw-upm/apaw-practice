package es.upm.miw.apaw.adapters.mongodb.shop.daos;

import es.upm.miw.apaw.adapters.mongodb.shop.entities.ArticleEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ArticleRepository extends MongoRepository<ArticleEntity, String> {
    Optional<ArticleEntity> findByBarcode(String barcode);
}
