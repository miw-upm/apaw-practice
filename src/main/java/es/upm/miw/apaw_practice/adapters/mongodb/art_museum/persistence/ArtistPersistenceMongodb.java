package es.upm.miw.apaw_practice.adapters.mongodb.art_museum.persistence;

import es.upm.miw.apaw_practice.adapters.mongodb.art_museum.daos.ArtistRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.art_museum.entities.ArtistEntity;
import es.upm.miw.apaw_practice.domain.models.art_museum.Artist;
import es.upm.miw.apaw_practice.domain.persistence_ports.art_museum.ArtistPersistence;
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
    public Artist create(Artist artist) {
        return this.artistRepository
                .save(new ArtistEntity(artist))
                .toArtist();
    }

    @Override
    public boolean existArtistName(String artistName) {
        return this.artistRepository
                .findByArtistName(artistName)
                .isPresent();
    }
}
