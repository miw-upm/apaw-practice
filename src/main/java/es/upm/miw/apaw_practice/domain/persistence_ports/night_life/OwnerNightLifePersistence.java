package es.upm.miw.apaw_practice.domain.persistence_ports.night_life;
import es.upm.miw.apaw_practice.domain.models.night_life.Owner;
import org.springframework.stereotype.Repository;

@Repository
public interface OwnerNightLifePersistence {
    Owner create(Owner owner);
}
