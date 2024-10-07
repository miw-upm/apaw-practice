package es.upm.miw.apaw_practice.adapters.mongodb.theme_park.daos;

import es.upm.miw.apaw_practice.adapters.mongodb.theme_park.entities.ThemeParkEntity;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface ThemeParkRepository extends MongoRepository<ThemeParkEntity, String> {
}
