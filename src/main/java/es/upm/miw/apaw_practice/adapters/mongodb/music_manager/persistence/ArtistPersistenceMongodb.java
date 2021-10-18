package es.upm.miw.apaw_practice.adapters.mongodb.music_manager.persistence;

import es.upm.miw.apaw_practice.adapters.mongodb.music_manager.daos.ArtistRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.music_manager.entities.ArtistEntity;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.models.music_manager.Artist;
import es.upm.miw.apaw_practice.domain.persistence_ports.music_manager.ArtistPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.stream.Stream;

@Repository("artistPersistence")
public class ArtistPersistenceMongodb implements ArtistPersistence {
    private final ArtistRepository artistRepository;
    private static final String ARTISTNOTFOUND = "Artist not found:";

    @Autowired
    public ArtistPersistenceMongodb(ArtistRepository artistRepository) {
        this.artistRepository = artistRepository;
    }

    @Override
    public Artist readByFirstNameAndFamilyName(String firstName, String familyName) {
        return this.artistRepository
                .findByFirstNameAndFamilyName(firstName, familyName)
                .orElseThrow(() -> new NotFoundException(ARTISTNOTFOUND + " " + firstName + " " + familyName))
                .toArtist();
    }

    @Override
    public Artist update(Artist artist) {
        ArtistEntity artistEntity = this.artistRepository
                .findByFirstNameAndFamilyName(artist.getFirstName(), artist.getFamilyName())
                .orElseThrow(() -> new NotFoundException(ARTISTNOTFOUND + " " +
                        artist.getFirstName() + " " + artist.getFamilyName()));
        artistEntity.fromArtist(artist);
        return artistRepository
                .save(artistEntity)
                .toArtist();
    }

    @Override
    public Artist readByFirstName(String firstName) {
        return this.artistRepository
                .findByFirstName(firstName)
                .orElseThrow(() -> new NotFoundException("Artist full name: " + firstName))
                .toArtist();
    }

    @Override
    public Stream<Artist> readAll() {
        return this.artistRepository.findAll()
                .stream().map(ArtistEntity::toArtist);
    }
}
