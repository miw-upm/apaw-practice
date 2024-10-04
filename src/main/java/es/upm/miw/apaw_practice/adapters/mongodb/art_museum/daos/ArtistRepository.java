package es.upm.miw.apaw_practice.adapters.mongodb.art_museum.daos;

import es.upm.miw.apaw_practice.adapters.mongodb.art_museum.entities.ArtistEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ArtistRepository extends MongoRepository<ArtistEntity, String> {

}
