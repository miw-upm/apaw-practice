package es.upm.miw.apaw.domain.services.metro;

import es.upm.miw.apaw.domain.models.metro.TrainLine;
import es.upm.miw.apaw.domain.exceptions.ConflictException;
import es.upm.miw.apaw.domain.persistenceports.metro.TrainLinePersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class TrainLineService {

    private final TrainLinePersistence trainLinePersistence;

    @Autowired
    public TrainLineService(TrainLinePersistence trainLinePersistence) {
        this.trainLinePersistence = trainLinePersistence;
    }

    public TrainLine create(TrainLine trainLine) {
        this.assertNumberNotExists(trainLine.getNumber());
        return this.trainLinePersistence.create(trainLine);
    }

    public void assertNumberNotExists(Integer number) {
        if (this.trainLinePersistence.existNumber(number)) {
            throw new ConflictException("Number exists: " + number);
        }
    }
}