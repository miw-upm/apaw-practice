package es.upm.miw.apaw_practice.domain.persistence_ports.art_museum;

import es.upm.miw.apaw_practice.domain.models.art_museum.Artist;
import org.springframework.stereotype.Repository;

import java.util.stream.Stream;

@Repository
public interface ArtistPersistence {

    Artist create(Artist artist);

    boolean existArtistName(String artistName);

    Stream<String> findByExhibitionNameDistinctArtStyles(String exhibitionName);
}
