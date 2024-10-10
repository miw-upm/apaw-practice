package es.upm.miw.apaw_practice.domain.persistence_ports.night_life;
import es.upm.miw.apaw_practice.domain.models.night_life.Owner;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface OwnerNightLifePersistence {
    Owner create(Owner owner);
    Optional<Owner> readByName(String name);
}
