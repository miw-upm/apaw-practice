package es.upm.miw.apaw.adapters.mongodb.music.persistence;

import es.upm.miw.apaw.adapters.mongodb.music.daos.ArtistRepository;
import es.upm.miw.apaw.adapters.mongodb.music.daos.SongRepository;
import es.upm.miw.apaw.adapters.mongodb.music.daos.StyleRepository;
import es.upm.miw.apaw.adapters.mongodb.music.entities.ArtistEntity;
import es.upm.miw.apaw.adapters.mongodb.music.entities.SongEntity;
import es.upm.miw.apaw.adapters.mongodb.music.entities.StyleEntity;
import es.upm.miw.apaw.domain.models.UserDto;
import es.upm.miw.apaw.domain.models.music.Artist;
import es.upm.miw.apaw.domain.persistenceports.music.ArtistPersistence;
import es.upm.miw.apaw.domain.restclients.UserRestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Stream;

@Repository("artistPersistenceMongodb")
public class ArtistPersistenceMongodb implements ArtistPersistence {

    private final ArtistRepository artistRepository;
    private final SongRepository songRepository;
    private final StyleRepository styleRepository;
    private final UserRestClient userRestClient;

    @Autowired
    public ArtistPersistenceMongodb(ArtistRepository artistRepository,
                                    SongRepository songRepository,
                                    StyleRepository styleRepository,
                                    UserRestClient userRestClient) {
        this.artistRepository = artistRepository;
        this.songRepository = songRepository;
        this.styleRepository = styleRepository;
        this.userRestClient = userRestClient;
    }

    @Override
    public Stream<Artist> readByName(String name) {
        return this.artistRepository.findById(name)
                .map(this::toArtist)
                .stream();
    }

    private Artist toArtist(ArtistEntity entity) {
        UserDto user = this.userRestClient.readById(UUID.fromString(entity.getUserId()));
        return new Artist(
                entity.getName(),
                entity.getActiveSince(),
                entity.getMonthlyListeners(),
                user,
                null
        );
    }

    @Override
    public Stream<String> findMoodsByUserMobile(String mobile) {
        UserDto user = this.userRestClient.readByMobile(mobile);
        String userId = user.getId().toString();

        return this.artistRepository.findAll().stream()
                .filter(artist -> userId.equals(artist.getUserId()))
                .flatMap(artist -> artist.getSongIsrcs() == null
                        ? Stream.empty()
                        : artist.getSongIsrcs().stream())
                .map(this.songRepository::findById)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .map(SongEntity::getStyleGenre)
                .filter(Objects::nonNull)
                .map(this.styleRepository::findById)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .map(StyleEntity::getMood)
                .filter(Objects::nonNull)
                .distinct();
    }
}