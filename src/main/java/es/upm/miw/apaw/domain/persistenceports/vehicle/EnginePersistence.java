package es.upm.miw.apaw.domain.persistenceports.vehicle;

import es.upm.miw.apaw.domain.models.vehicle.Engine;
import org.springframework.stereotype.Repository;

@Repository
public interface EnginePersistence {

    boolean existCodeEngine(String codeEngine);

    Engine update(Engine engine);

    Engine create(Engine engine);
}
