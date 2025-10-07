package es.upm.miw.apaw.adapters.mongodb.vehicle.persistence;

import es.upm.miw.apaw.adapters.mongodb.vehicle.daos.EngineRepository;
import es.upm.miw.apaw.adapters.mongodb.vehicle.entities.EngineEntity;
import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.models.vehicle.Engine;
import es.upm.miw.apaw.domain.persistenceports.vehicle.EnginePersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("enginePersistence")
public class EnginePersistenceMongodb implements EnginePersistence {

    private final EngineRepository engineRepository;

    @Autowired
    public EnginePersistenceMongodb(EngineRepository engineRepository) {
        this.engineRepository = engineRepository;
    }

    @Override
    public boolean existCodeEngine(String codeEngine) {
        return this.engineRepository
                .findByCodeEngine(codeEngine)
                .isPresent();
    }

    @Override
    public Engine create(Engine engine) {
        EngineEntity engineEntity = new EngineEntity(engine);
        return this.engineRepository.save(engineEntity).toEngine();
    }
}
