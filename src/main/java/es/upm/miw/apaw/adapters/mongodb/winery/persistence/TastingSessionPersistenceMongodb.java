package es.upm.miw.apaw.adapters.mongodb.winery.persistence;

import es.upm.miw.apaw.adapters.mongodb.winery.daos.TastingSessionRepository;
import es.upm.miw.apaw.adapters.mongodb.winery.entities.EvaluationEntity;
import es.upm.miw.apaw.adapters.mongodb.winery.entities.TastingSessionEntity;
import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.models.winery.TastingSession;
import es.upm.miw.apaw.domain.persistenceports.winery.TastingSessionPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

@Repository("tastingSessionPersistence")
public class TastingSessionPersistenceMongodb implements TastingSessionPersistence {

    private final TastingSessionRepository tastingSessionRepository;

    @Autowired
    public TastingSessionPersistenceMongodb(TastingSessionRepository tastingSessionRepository) {
        this.tastingSessionRepository = tastingSessionRepository;
    }

    @Override
    public Stream<TastingSession> readAll() {
        return this.tastingSessionRepository.findAll().stream()
                .map(TastingSessionEntity::toTastingSession);
    }

    @Override
    public TastingSession readById(UUID id) {
        return this.tastingSessionRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(" TastingSession id: " + id))
                .toTastingSession();
    }

    @Override
    public TastingSession update(TastingSession tastingSession) {
        TastingSessionEntity tastingSessionEntity = this.tastingSessionRepository
                .findById(tastingSession.getId())
                .orElseThrow(() -> new NotFoundException(" TastingSession id: " + tastingSession.getId()));
        List<EvaluationEntity> evaluationEntities = tastingSession.getEvaluations().stream()
                .map(evaluation -> new EvaluationEntity(
                        evaluation.getScore(),
                        evaluation.getComment(),
                        evaluation.getRecommended())
                ).toList();
        tastingSessionEntity.setEvaluationEntities(evaluationEntities);
        return this.tastingSessionRepository.save(tastingSessionEntity).toTastingSession();
    }
}
