package es.upm.miw.apaw_practice.domain.persistence_ports.music_manager;

import es.upm.miw.apaw_practice.domain.models.music_manager.Artist;
import org.springframework.stereotype.Repository;

import java.util.stream.Stream;

@Repository
public interface ArtistPersistence {
    Artist readByFirstNameAndFamilyName(String firstName, String familyName);

    Artist update(Artist artist);

    Artist readByFirstName(String firstName);

    Stream<Artist> readAll();

}
