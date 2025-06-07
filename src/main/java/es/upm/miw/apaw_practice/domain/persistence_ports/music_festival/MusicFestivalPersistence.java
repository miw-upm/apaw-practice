package es.upm.miw.apaw_practice.domain.persistence_ports.music_festival;

import es.upm.miw.apaw_practice.domain.models.music_festival.MusicFestival;
import java.math.BigDecimal;
import java.util.stream.Stream;
import org.springframework.stereotype.Repository;

@Repository
public interface MusicFestivalPersistence {

    MusicFestival readByName(String name);

    Stream<MusicFestival> readAll();

    MusicFestival update(MusicFestival musicFestival);

    MusicFestival updateBudget(String name, BigDecimal budget);

}
