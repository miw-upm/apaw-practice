package es.upm.miw.apaw.domain.persistenceports.music;

import es.upm.miw.apaw.domain.models.music.Artist;

import java.util.stream.Stream;


public interface ArtistPersistence {

    Stream<Artist> readByName(String name);

    Stream<String> findMoodsByUserMobile(String mobile);
}
