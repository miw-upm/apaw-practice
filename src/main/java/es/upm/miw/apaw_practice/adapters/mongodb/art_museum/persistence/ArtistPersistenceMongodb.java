package es.upm.miw.apaw_practice.adapters.mongodb.art_museum.persistence;

import es.upm.miw.apaw_practice.adapters.mongodb.art_museum.daos.ArtistRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.art_museum.daos.MuseumRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.art_museum.entities.ArtistEntity;
import es.upm.miw.apaw_practice.domain.models.art_museum.Artist;
import es.upm.miw.apaw_practice.domain.persistence_ports.art_museum.ArtistPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.stream.Stream;

@Repository("artistPersistence")
public class ArtistPersistenceMongodb implements ArtistPersistence {
    private final ArtistRepository artistRepository;
    private final MuseumRepository museumRepository;

    @Autowired
    public ArtistPersistenceMongodb(ArtistRepository artistRepository, MuseumRepository museumRepository) {
        this.artistRepository = artistRepository;
        this.museumRepository = museumRepository;
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

    @Override
    public Stream<String> findByExhibitionNameDistinctArtStyles(String exhibitionName) {
        return this.museumRepository.findAll().stream()
                .filter(museum -> museum.getExhibitionEntities().stream()
                        .anyMatch(exhibition -> exhibition.getName().equals(exhibitionName)))
                .flatMap(museum -> museum.getArtworkEntities().stream())
                .map(artwork -> artwork.getArtist().getArtStyle())
                .distinct();
    }
}
