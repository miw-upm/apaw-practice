package es.upm.miw.apaw.adapters.mongodb.metro.persistence;
import es.upm.miw.apaw.adapters.mongodb.metro.daos.TrainLineRepository;
import es.upm.miw.apaw.adapters.mongodb.metro.entities.TrainLineEntity;
import es.upm.miw.apaw.domain.models.metro.TrainLine;
import es.upm.miw.apaw.domain.persistenceports.metro.TrainLinePersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("trainLinePersistence")
public class TrainLinePersistenceMongodb implements TrainLinePersistence {

    private final TrainLineRepository trainLineRepository;

    @Autowired
    public TrainLinePersistenceMongodb(TrainLineRepository trainLineRepository) {
        this.trainLineRepository = trainLineRepository;
    }

    @Override
    public TrainLine create(TrainLine trainLine) {
        return this.trainLineRepository
                .save(new TrainLineEntity(trainLine))
                .toTrainLine();
    }

    @Override
    public boolean existNumber(Integer number) {
        return this.trainLineRepository
                .findByNumber(number)
                .isPresent();
    }
}
