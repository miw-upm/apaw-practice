package es.upm.miw.apaw_practice.domain.services.music_manager;

import es.upm.miw.apaw_practice.domain.models.music_manager.Album;
import es.upm.miw.apaw_practice.domain.models.music_manager.Artist;
import es.upm.miw.apaw_practice.domain.persistence_ports.music_manager.AlbumPersistence;
import es.upm.miw.apaw_practice.domain.persistence_ports.music_manager.ArtistPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArtistService {

    private final ArtistPersistence artistPersistence;

    @Autowired
    public ArtistService(ArtistPersistence artistPersistence) {
        this.artistPersistence = artistPersistence;
    }

    @Autowired
    AlbumPersistence albumPersistence;

    public Artist updateAge(String fullName, Integer age) {
        String[] splittedName = fullName.split(" ");

        Artist artist = this.artistPersistence.readByFirstNameAndFamilyName(splittedName[0], splittedName[1]);
        artist.setAge(age);
        return this.artistPersistence.update(artist);
    }

    public Integer findSumAgeBySongGenre(String genre) {
        return this.albumPersistence.readAll()
                .filter(album -> album.getTracks().stream()
                        .anyMatch(song -> song.getGenre().equals(genre)))
                .map(Album::getBand)
                .distinct()
                .flatMap(band -> band.getArtists().stream())
                .distinct()
                .map(Artist::getAge)
                .reduce(Integer::sum)
                .orElseThrow();
    }
}
