package es.upm.miw.apaw_practice.domain.persistence_ports.emarketer;

import es.upm.miw.apaw_practice.domain.models.emarketer.Cups;
import org.springframework.stereotype.Repository;

@Repository
public interface CupsPersistence {
    Cups readByCup(String cups);

    void update(Cups cupsResult);
}
