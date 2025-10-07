package es.upm.miw.apaw.domain.services.vehicle;

import es.upm.miw.apaw.domain.exceptions.ConflictException;
import es.upm.miw.apaw.domain.models.vehicle.Engine;
import es.upm.miw.apaw.domain.persistenceports.vehicle.EnginePersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EngineService {

    private final EnginePersistence enginePersistence;

    @Autowired
    public EngineService(EnginePersistence enginePersistence) {
        this.enginePersistence = enginePersistence;
    }

    public Engine create(Engine engine) {
        this.assertCodeEngineExist(engine.getCodeEngine());
        return this.enginePersistence.create(engine);
    }

    private void assertCodeEngineExist(String codeEngine) {
        if (this.enginePersistence.existCodeEngine(codeEngine)) {
            throw new ConflictException("Code engine exist: " + codeEngine);
        }
    }

}
