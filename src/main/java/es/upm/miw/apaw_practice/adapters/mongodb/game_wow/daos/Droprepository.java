package es.upm.miw.apaw_practice.adapters.mongodb.game_wow.daos;

import es.upm.miw.apaw_practice.adapters.mongodb.game_wow.entities.DropEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface Droprepository extends MongoRepository <DropEntity, String> {
}
