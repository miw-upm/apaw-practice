package es.upm.miw.apaw.domain.services.recruiting;

import es.upm.miw.apaw.domain.models.recruiting.Position;
import es.upm.miw.apaw.domain.persistenceports.recruiting.PositionPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PositionService {

    private final PositionPersistence positionPersistence;

    @Autowired
    public PositionService(PositionPersistence positionPersistence) {
        this.positionPersistence = positionPersistence;
    }

    public Position create(Position position) {
        return this.positionPersistence.create(position);
    }
}
