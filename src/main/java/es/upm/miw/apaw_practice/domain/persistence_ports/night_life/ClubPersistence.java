package es.upm.miw.apaw_practice.domain.persistence_ports.night_life;
import es.upm.miw.apaw_practice.domain.models.night_life.Club;
import org.springframework.stereotype.Repository;
import java.util.stream.Stream;
@Repository
public interface ClubPersistence {
    Stream<Club> readAll();

}
