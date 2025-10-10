package es.upm.miw.apaw.domain.services.winery;

import es.upm.miw.apaw.domain.models.winery.Evaluation;
import es.upm.miw.apaw.domain.models.winery.TastingSession;
import es.upm.miw.apaw.domain.persistenceports.winery.TastingSessionPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TastingSessionService {

    private final TastingSessionPersistence tastingSessionPersistence;

    @Autowired
    public TastingSessionService(TastingSessionPersistence tastingSessionPersistence) {
        this.tastingSessionPersistence = tastingSessionPersistence;
    }

    public TastingSession read(UUID id) {
        return this.tastingSessionPersistence.readById(id);
    }

    public TastingSession updateEvaluations(UUID id, List<Evaluation> evaluationList) {
        TastingSession tastingSession = this.tastingSessionPersistence.readById(id);
        tastingSession.setEvaluations(evaluationList);
        return this.tastingSessionPersistence.update(tastingSession);
    }
}
