package es.upm.miw.apaw_practice.domain.persistence_ports.art_museum;

import es.upm.miw.apaw_practice.domain.models.art_museum.Museum;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface MuseumPersistence {

    Museum readByName(String name);

    void deleteByName(String name);

    Museum updateExhibitionPrice(Museum museum, String exhibitionName);

    BigDecimal findByArtistNameSumPricesExhibitions(String artistName);
}
