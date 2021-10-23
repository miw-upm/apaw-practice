package es.upm.miw.apaw_practice.adapters.mongodb.music_manager;

import es.upm.miw.apaw_practice.adapters.mongodb.music_manager.daos.AlbumRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.music_manager.daos.ArtistRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.music_manager.daos.BandRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.music_manager.daos.SongRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.music_manager.entities.AlbumEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.music_manager.entities.ArtistEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.music_manager.entities.BandEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.music_manager.entities.SongEntity;
import es.upm.miw.apaw_practice.domain.models.music_manager.Artist;
import es.upm.miw.apaw_practice.domain.models.music_manager.Song;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Service
public class MusicManagerSeederService {

    @Autowired
    ArtistRepository artistRepository;
    @Autowired
    BandRepository bandRepository;
    @Autowired
    AlbumRepository albumRepository;
    @Autowired
    SongRepository songRepository;

    public void seedDatabase() {
        LogManager.getLogger(this.getClass()).warn("------- Music Manager Initial Load -----------");
        ArtistEntity[] artists = {
                new ArtistEntity(Artist.builder().firstName("John").familyName("Lennon").age(40).build()),
                new ArtistEntity(Artist.builder().firstName("Paul").familyName("McCartney").age(79).build()),
                new ArtistEntity(Artist.builder().firstName("George").familyName("Harrison").age(58).build()),
                new ArtistEntity(Artist.builder().firstName("Ringo").familyName("Starr").age(81).build()),
                new ArtistEntity(Artist.builder().firstName("Kurt").familyName("Cobain").age(27).build()),
                new ArtistEntity(Artist.builder().firstName("Dave").familyName("Grohl").age(52).build()),
                new ArtistEntity(Artist.builder().firstName("Krist").familyName("Novoselic").age(56).build()),
                new ArtistEntity(Artist.builder().firstName("Nate").familyName("Mendel").age(52).build()),
                new ArtistEntity(Artist.builder().firstName("Pat").familyName("Smear").age(62).build())
        };
        this.artistRepository.saveAll(Arrays.asList(artists));
        BandEntity[] bands = {
                new BandEntity("The Beatles", "Liverpool, England", false, List.of(artists[0], artists[1], artists[2], artists[3])),
                new BandEntity("Nirvana", "Aberdeen, Washington, U.S.", false, List.of(artists[4], artists[5], artists[6])),
                new BandEntity("Foo Fighters", "Seattle, Washington, U.S.", true, List.of(artists[5], artists[7], artists[8]))
        };
        this.bandRepository.saveAll(Arrays.asList(bands));
        SongEntity[] songs = {
                new SongEntity(new Song("Blackbird", "Folk", 139)),
                new SongEntity(new Song("Helter Skelter", "Hard Rock", 269)),
                new SongEntity(new Song("Smells Like Teen Spirit", "Grunge", 301)),
                new SongEntity(new Song("Heart-Shaped Box", "Grunge", 279)),
                new SongEntity(new Song("The Pretender", "Hard Rock", 267))
        };
        this.songRepository.saveAll(Arrays.asList(songs));
        AlbumEntity[] albums = {
                new AlbumEntity(bands[0], List.of(songs[0], songs[1]), "The Beatles", "Apple", new BigDecimal("6.99"), LocalDate.of(1968, 11, 22)),
                new AlbumEntity(bands[1], List.of(songs[2]), "Nevermind", "DGC", new BigDecimal("8.99"), LocalDate.of(1991, 9, 24)),
                new AlbumEntity(bands[1], List.of(songs[3]), "In Utero", "DGC", new BigDecimal("5.99"), LocalDate.of(1993, 9, 13)),
                new AlbumEntity(bands[2], List.of(songs[4]), "Echoes, Silence, Patience & Grace", "Roswell", new BigDecimal("7.99"), LocalDate.of(2007, 8, 21))
        };
        this.albumRepository.saveAll(Arrays.asList(albums));
    }

    public void deleteAll() {
        this.albumRepository.deleteAll();
        this.songRepository.deleteAll();
        this.bandRepository.deleteAll();
        this.artistRepository.deleteAll();
    }
}

