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
import java.util.UUID;

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

        GenreEntity genre = genreRepository.findByType("rol")
                .orElseThrow(() -> new RuntimeException("Género 'rol' no encontrado"));

        List<VideogameEntity> beforeUpdate = videogameRepository.findByGenreEntityId(genre.getId());
        assertThat(beforeUpdate).isNotEmpty();

        beforeUpdate.forEach(v -> v.setOnline(false));
        videogameRepository.saveAll(beforeUpdate);

        List<VideogameEntity> afterUpdate = videogameRepository.findByGenreEntityId(genre.getId());

        assertThat(afterUpdate).isNotEmpty();
        assertThat(afterUpdate).allMatch(v -> !v.getOnline());
    }
    @Test
    void testDeleteByName() {
        assertThat(videogameRepository.findAll()).extracting("name").contains("game0");

        videogameRepository.deleteByName("game0");

        assertThat(videogameRepository.findAll()).extracting("name").doesNotContain("game0");
    }
    @Test
    void testFindByGenreEntityId() {
        UUID genreIdRol = UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0001"); // género "rol"

        List<VideogameEntity> games = videogameRepository.findByGenreEntityId(genreIdRol);

        // Debe devolver los juegos game1 y game3
        assertThat(games).extracting("name").containsExactlyInAnyOrder("game1", "game3");
    }

}
