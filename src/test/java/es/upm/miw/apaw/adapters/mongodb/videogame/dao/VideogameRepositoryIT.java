package es.upm.miw.apaw.adapters.mongodb.videogame.dao;

import es.upm.miw.apaw.adapters.mongodb.videogame.daos.GenreRepository;
import es.upm.miw.apaw.adapters.mongodb.videogame.daos.VideogameRepository;
import es.upm.miw.apaw.adapters.mongodb.videogame.entities.GenreEntity;
import es.upm.miw.apaw.adapters.mongodb.videogame.entities.VideogameEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
public class VideogameRepositoryIT {

    @Autowired
    private VideogameRepository videogameRepository;
    @Autowired
    private GenreRepository genreRepository;

    @Test
    void testUpdateOnlineByGenre() {
        GenreEntity genre = new GenreEntity();
        genre.setType("action");
        genreRepository.save(genre);

        VideogameEntity v1 = new VideogameEntity();
        v1.setName("Halo");
        v1.setOnline(true);
        v1.setGenreEntity(genre);

        VideogameEntity v2 = new VideogameEntity();
        v2.setName("COD");
        v2.setOnline(true);
        v2.setGenreEntity(genre);

        videogameRepository.saveAll(List.of(v1, v2));

        // Actualizar online = false
        List<VideogameEntity> beforeUpdate = videogameRepository.findByGenreEntityId(genre.getId());
        beforeUpdate.forEach(v -> v.setOnline(false));
        videogameRepository.saveAll(beforeUpdate);

        List<VideogameEntity> afterUpdate = videogameRepository.findByGenreEntityId(genre.getId());
        assertThat(afterUpdate).allMatch(v -> !v.getOnline());
    }

}
