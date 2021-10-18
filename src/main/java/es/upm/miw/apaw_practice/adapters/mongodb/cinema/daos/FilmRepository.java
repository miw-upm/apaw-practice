package es.upm.miw.apaw_practice.adapters.mongodb.cinema.daos;

import es.upm.miw.apaw_practice.adapters.mongodb.cinema.entities.FilmEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FilmRepository extends MongoRepository<FilmEntity, String> {

    void deleteByBarcode(String barcode);
}
