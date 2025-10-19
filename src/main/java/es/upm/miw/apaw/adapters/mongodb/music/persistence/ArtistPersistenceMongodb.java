package es.upm.miw.apaw.adapters.mongodb.music.persistence;

import es.upm.miw.apaw.adapters.mongodb.music.daos.ArtistRepository;
import es.upm.miw.apaw.adapters.mongodb.music.entities.ArtistEntity;
import es.upm.miw.apaw.domain.models.UserDto;
import es.upm.miw.apaw.domain.models.music.Artist;
import es.upm.miw.apaw.domain.persistenceports.music.ArtistPersistence;
import es.upm.miw.apaw.domain.restclients.UserRestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.UUID;
import java.util.stream.Stream;

@Repository("artistPersistenceMongodb")
public class ArtistPersistenceMongodb implements ArtistPersistence {

    private final ArtistRepository artistRepository;
    private final UserRestClient userRestClient;

    @Autowired
    public ArtistPersistenceMongodb(ArtistRepository artistRepository,
                                    UserRestClient userRestClient) {
        this.artistRepository = artistRepository;
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
}
