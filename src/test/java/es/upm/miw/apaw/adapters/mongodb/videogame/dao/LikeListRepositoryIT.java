package es.upm.miw.apaw.adapters.mongodb.videogame.dao;


import es.upm.miw.apaw.adapters.mongodb.videogame.daos.LikeListRepository;
import es.upm.miw.apaw.adapters.mongodb.videogame.entities.GenreEntity;
import es.upm.miw.apaw.adapters.mongodb.videogame.entities.LikeListEntity;
import es.upm.miw.apaw.adapters.mongodb.videogame.entities.VideogameEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@ActiveProfiles("test")
public class LikeListRepositoryIT {

    @Autowired
    private LikeListRepository likeListRepository;

    @Test
    void testFindById() {

        assertTrue(this.likeListRepository.findById(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0020")).isPresent());
        LikeListEntity likeList = this.likeListRepository.findById(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0020")).get();
        assertThat(likeList.getLikesCount()).isEqualTo(0);
        assertThat(likeList.getGamesLikedEntity()).isEqualTo(Arrays.asList(VideogameEntity.builder().id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0100")).genreEntity( GenreEntity.builder().id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0000"))
                                .type("action").description("Accion").ageRestriction(10).build()).name("game0").online(false).releaseDate(LocalDate.now()).maxPlayers(10).build(),
                VideogameEntity.builder().id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0101")).genreEntity(GenreEntity.builder().id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0001"))
                                .type("rol").description("Rol").ageRestriction(15).build() ).name("game1").online(true).releaseDate(LocalDate.now()).maxPlayers(1).build()

                ));
        assertThat(likeList.getUserId()).isEqualTo(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0003"));

    }
}
