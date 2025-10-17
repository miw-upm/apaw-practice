package es.upm.miw.apaw.domain.persistenceports.metro;
import es.upm.miw.apaw.domain.models.metro.TrainLine;

public interface TrainLinePersistence {
    TrainLine create(TrainLine trainLine);
    boolean existNumber(Integer number);
}
