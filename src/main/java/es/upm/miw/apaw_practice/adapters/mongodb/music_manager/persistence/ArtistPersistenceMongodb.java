package es.upm.miw.apaw_practice.adapters.mongodb.music_manager.persistence;

import es.upm.miw.apaw_practice.adapters.mongodb.music_manager.daos.ArtistRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.music_manager.entities.ArtistEntity;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.models.music_manager.Artist;
import es.upm.miw.apaw_practice.domain.persistence_ports.music_manager.ArtistPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("artistPersistence")
public class ArtistPersistenceMongodb implements ArtistPersistence {
    private final ArtistRepository artistRepository;

    @Autowired
    public ArtistPersistenceMongodb(ArtistRepository artistRepository) {
        this.artistRepository = artistRepository;
    }

    @Override
    public Artist update(String id, Artist artist) {
        ArtistEntity artistEntity = this.artistRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException("Artist ID: " + id));
        artistEntity.fromArtist(artist);
        return artistRepository
                .save(artistEntity)
                .toArtist();
    }
}
