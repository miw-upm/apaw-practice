package es.upm.miw.apaw_practice.adapters.mongodb.art_museum;

import es.upm.miw.apaw_practice.adapters.mongodb.art_museum.daos.ArtistRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.art_museum.daos.ArtworkRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.art_museum.daos.MuseumRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.art_museum.entities.ArtistEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.art_museum.entities.ArtworkEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.art_museum.entities.ExhibitionEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.art_museum.entities.MuseumEntity;
import es.upm.miw.apaw_practice.domain.models.art_museum.Artist;
import es.upm.miw.apaw_practice.domain.models.art_museum.Artwork;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Service
public class ArtMuseumSeederService {
    @Autowired
    private ArtistRepository artistRepository;
    @Autowired
    private ArtworkRepository artworkRepository;
    @Autowired
    private MuseumRepository museumRepository;

    public void seedDatabase() {
        LogManager.getLogger(this.getClass()).warn("------- Art Museum Initial Load -----------");

        ArtistEntity[] artists = {
                new ArtistEntity(new Artist("Leonardo da Vinci", 67, "Renaissance")),
                new ArtistEntity(new Artist("Vincent van Gogh", 37, "Post-Impressionism")),
                new ArtistEntity(new Artist("Pablo Picasso", 91, "Cubism")),
                new ArtistEntity(new Artist("Diego Velázquez", 61, "Baroque"))
        };
        this.artistRepository.saveAll(Arrays.asList(artists));

        ArtworkEntity[] artworks = {
                new ArtworkEntity("27001", "La Gioconda", 1506, artists[0]),
                new ArtworkEntity("27002", "The Starry Night", 1889, artists[1]),
                new ArtworkEntity("27003", "Vase with Fifteen Sunflowers", 1888, artists[1]),
                new ArtworkEntity("27004", "Las Meninas", 1656, artists[3]),
                new ArtworkEntity("27005", "Guernica", 1937, artists[2]),
                new ArtworkEntity("27006", "The Last Supper", 1498, artists[0]),
        };
        this.artworkRepository.saveAll(Arrays.asList(artworks));

        ExhibitionEntity[] exhibitions = {
                new ExhibitionEntity("Spanish authors", LocalDateTime.of(2024, 10, 10,10, 0), BigDecimal.valueOf(35.00)),
                new ExhibitionEntity("Impressionist authors", LocalDateTime.of(2024, 12, 12, 10, 0), BigDecimal.valueOf(40.00)),
                new ExhibitionEntity("Century 15th", LocalDateTime.of(2025, 5, 20, 11, 0), BigDecimal.valueOf(15.00))
        };
        MuseumEntity[] museums = {
                new MuseumEntity("El Prado", 600, true, List.of(artworks[3], artworks[4]), List.of(exhibitions[0])),
                new MuseumEntity("Thyssen", 300, false, List.of(artworks[0], artworks[1], artworks[2], artworks[5]), List.of(exhibitions[1], exhibitions[2]))
        };
        this.museumRepository.saveAll(Arrays.asList(museums));
    }

    public void deleteAll() {
        this.museumRepository.deleteAll();
        this.artworkRepository.deleteAll();
        this.artistRepository.deleteAll();
    }
}
