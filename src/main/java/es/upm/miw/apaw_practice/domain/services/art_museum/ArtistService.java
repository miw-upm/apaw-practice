package es.upm.miw.apaw_practice.domain.services.art_museum;

import es.upm.miw.apaw_practice.domain.exceptions.ConflictException;
import es.upm.miw.apaw_practice.domain.models.art_museum.Artist;
import es.upm.miw.apaw_practice.domain.persistence_ports.art_museum.ArtistPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Stream;

@Service
public class ArtistService {
    private final ArtistPersistence artistPersistence;

    @Autowired
    public ArtistService(ArtistPersistence artistPersistence) {
        this.artistPersistence = artistPersistence;
    }

    public Artist create(Artist artist) {
        this.assertArtistNameNotExist(artist.getArtistName());
        return this.artistPersistence.create(artist);
    }

    public void assertArtistNameNotExist(String artistName) {
        if(this.artistPersistence.existArtistName(artistName)) {
            throw new ConflictException(" Artist exist: " + artistName);
        }
    }

    public Stream<String> findByExhibitionNameDistinctArtStyles(String exhibitionName) {
        return this.artistPersistence.findByExhibitionNameDistinctArtStyles(exhibitionName);
    }
}
