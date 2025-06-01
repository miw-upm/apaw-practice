package es.upm.miw.apaw_practice.adapters.mongodb.music_festival.daos;

import es.upm.miw.apaw_practice.adapters.mongodb.music_festival.entities.MusicFestivalEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MusicFestivalRepository extends MongoRepository<MusicFestivalEntity, String> {
}
