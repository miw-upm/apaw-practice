package es.upm.miw.apaw.adapters.mongodb.videogame.dao;

import es.upm.miw.apaw.adapters.mongodb.videogame.daos.GenreRepository;
import es.upm.miw.apaw.adapters.mongodb.videogame.entities.GenreEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@ActiveProfiles("test")
public class GenreRepositoryIT {

    @Autowired
    private GenreRepository genreRepository;

    @Test
    void testFindByType() {
        assertTrue(this.genreRepository.findByType("action").isPresent());
        GenreEntity genre = this.genreRepository.findByType("action").get();
        assertThat(genre.getId()).isEqualTo(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0000"));
        assertThat(genre.getDescription()).isEqualTo("Accion");
        assertThat(genre.getAgeRestriction()).isEqualTo(10);
    }
}
