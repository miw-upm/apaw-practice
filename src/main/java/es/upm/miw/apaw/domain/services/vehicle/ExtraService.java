package es.upm.miw.apaw.domain.services.vehicle;

import es.upm.miw.apaw.domain.persistenceports.vehicle.ExtraPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ExtraService {

    private final ExtraPersistence extraPersistence;

    @Autowired
    public ExtraService(ExtraPersistence extraPersistence) {
        this.extraPersistence = extraPersistence;
    }

    public void delete(UUID id) {
        this.extraPersistence.delete(id);
    }
}
