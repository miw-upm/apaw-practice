package es.upm.miw.apaw.adapters.mongodb.videogame.daos;

import es.upm.miw.apaw.adapters.mongodb.videogame.entities.CompanyEntity;
import es.upm.miw.apaw.adapters.mongodb.videogame.entities.GenreEntity;
import es.upm.miw.apaw.adapters.mongodb.videogame.entities.LikeListEntity;
import es.upm.miw.apaw.adapters.mongodb.videogame.entities.VideogameEntity;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.UUID;

@Repository
@Profile({"dev", "test"})
@Log4j2
public class VideogameSeeder {
    private final GenreRepository genreRepository;
    private final CompanyRepository companyRepository;
    private final LikeListRepository likeListRepository;
    private final VideogameRepository videogameRepository;

    public VideogameSeeder(GenreRepository genreRepository, CompanyRepository companyRepository, LikeListRepository likeListRepository, VideogameRepository videogameRepository) {
        this.genreRepository = genreRepository;
        this.companyRepository = companyRepository;
        this.likeListRepository = likeListRepository;
        this.videogameRepository = videogameRepository;
    }

    public void seedDatabase() {
        log.warn("------- Shop Initial Load -----------");

        GenreEntity[] genres = {
                GenreEntity.builder().id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0000"))
                        .type("action").description("Accion").ageRestriction(10).build(),
                GenreEntity.builder().id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0001"))
                        .type("rol").description("Rol").ageRestriction(15).build(),
                GenreEntity.builder().id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0002"))
                        .type("music").description("Musica").ageRestriction(18).build(),
                GenreEntity.builder().id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0003"))
                        .type("puzzle").description("puzle").ageRestriction(15).build(),
                GenreEntity.builder().id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0004"))
                        .type("casual").description("Casual").ageRestriction(18).build()
        };
        this.genreRepository.saveAll(Arrays.asList(genres));

        VideogameEntity[] videogames = {
                VideogameEntity.builder().id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0100")).genreEntity(genres[0]).name("game0").online(false).releaseDate(LocalDate.now()).maxPlayers(10).build(),
                VideogameEntity.builder().id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0101")).genreEntity(genres[1]).name("game1").online(true).releaseDate(LocalDate.now()).maxPlayers(1).build(),
                VideogameEntity.builder().id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0102")).genreEntity(genres[2]).name("game2").online(true).releaseDate(LocalDate.now()).maxPlayers(2).build(),
                VideogameEntity.builder().id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0103")).genreEntity(genres[1]).name("game3").online(true).releaseDate(LocalDate.now()).maxPlayers(2).build(),
                VideogameEntity.builder().id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0104")).genreEntity(genres[4]).name("game4").online(false).releaseDate(LocalDate.now()).maxPlayers(10).build(),

        };
        CompanyEntity[] companies = {
                CompanyEntity.builder().id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0010"))
                        .denomination("company0").foundationDate(LocalDate.now()).sector("sector0")
                        .videoGamesEntity(Arrays.asList(videogames[1], videogames[2])).build(),
                CompanyEntity.builder().id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0011"))
                        .denomination("company1").foundationDate(LocalDate.now()).sector("sector1").build(),
                CompanyEntity.builder().id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0012"))
                        .denomination("company2").foundationDate(LocalDate.now()).sector("sector0")
                        .videoGamesEntity(Arrays.asList(videogames[3], videogames[4])).build(),
        };
        this.companyRepository.saveAll(Arrays.asList(companies));

        LikeListEntity[] likeLists = {
                LikeListEntity.builder().id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0020"))
                        .likesCount(0).shared(true).gamesLikedEntity(Arrays.asList(videogames[0], videogames[1]))
                        .userId(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0003")).build(),
                LikeListEntity.builder().id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0021"))
                        .likesCount(10).shared(false).gamesLikedEntity(Arrays.asList(videogames[0], videogames[2]))
                        .userId(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0002")).build(),
                LikeListEntity.builder().id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0022"))
                        .likesCount(5).shared(true)
                        .userId(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0004")).build()
        };
        this.likeListRepository.saveAll(Arrays.asList(likeLists));
    }

    public void deleteAll() {
        this.likeListRepository.deleteAll();
        this.companyRepository.deleteAll();
        this.genreRepository.deleteAll();
        this.videogameRepository.deleteAll();
    }
}
