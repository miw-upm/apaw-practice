package es.upm.miw.apaw.domain.services.recruiting;

import es.upm.miw.apaw.domain.models.recruiting.Position;
import es.upm.miw.apaw.domain.models.recruiting.PositionNumVacanciesUpdating;
import es.upm.miw.apaw.domain.persistenceports.recruiting.PositionPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Stream;

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

    public void updateNumVacancies(Stream<PositionNumVacanciesUpdating> positionNumVacanciesUpdating) {
        positionNumVacanciesUpdating.map( positionNewNumVacancies -> {
                Position position = this.positionPersistence.read(positionNewNumVacancies.getReference());
                position.setNumVacancies(positionNewNumVacancies.getNumVacancies());
                return position;
            })
            .forEach( position -> this.positionPersistence.update(position.getReference(), position));
    }

    public Position read(int reference) {
        return positionPersistence.read(reference);
    }
}