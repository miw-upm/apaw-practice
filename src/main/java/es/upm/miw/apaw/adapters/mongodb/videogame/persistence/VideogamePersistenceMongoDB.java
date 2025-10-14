package es.upm.miw.apaw.adapters.mongodb.videogame.persistence;

import es.upm.miw.apaw.adapters.mongodb.videogame.daos.VideogameRepository;
import es.upm.miw.apaw.adapters.mongodb.videogame.entities.VideogameEntity;
import es.upm.miw.apaw.domain.models.videogame.Genre;
import es.upm.miw.apaw.domain.models.videogame.Videogame;
import es.upm.miw.apaw.domain.persistenceports.videogame.VideogamePersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Repository("videogamePersistence")
public class VideogamePersistenceMongoDB implements VideogamePersistence {

    private final VideogameRepository videogameRepository;

    @Autowired
    public VideogamePersistenceMongoDB(VideogameRepository videogameRepository){
        this.videogameRepository =videogameRepository;

    }
    @Override
     public void delete(String name){
        this.videogameRepository
                .deleteByName(name);

    }
    @Override
    public List<Videogame> findByGenre(String genreName) {
        return this.videogameRepository.findByGenre(genreName)
                .stream()
                .map(VideogameEntity::toVideogame)
                .toList();
    }
    @Override
    public void saveAll(List<Videogame> videogames) {
        List<VideogameEntity> entities = videogames.stream().map(videogame -> {
            VideogameEntity entity = new VideogameEntity();
            entity.fromVideogame(videogame); // copia todas las propiedades
            return entity;
        }).toList();

        this.videogameRepository.saveAll(entities);
    }

}
