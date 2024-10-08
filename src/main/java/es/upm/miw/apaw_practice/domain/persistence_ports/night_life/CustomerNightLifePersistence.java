package es.upm.miw.apaw_practice.domain.persistence_ports.night_life;
import org.springframework.stereotype.Repository;
@Repository
public interface CustomerNightLifePersistence {
    void delete(String name);
}
