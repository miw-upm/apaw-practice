package es.upm.miw.apaw.domain.services.metro;

import es.upm.miw.apaw.domain.models.metro.SumTicketPrices;
import es.upm.miw.apaw.domain.models.metro.TrainLine;
import es.upm.miw.apaw.domain.exceptions.ConflictException;
import es.upm.miw.apaw.domain.models.metro.TrainStation;
import es.upm.miw.apaw.domain.models.metro.Zone;
import es.upm.miw.apaw.domain.persistenceports.metro.TrainLinePersistence;
import es.upm.miw.apaw.domain.persistenceports.metro.TrainStationPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.math.BigDecimal;


@Service
public class TrainLineService {

    private final TrainLinePersistence trainLinePersistence;
    private final TrainStationPersistence trainStationPersistence;

    @Autowired
    public TrainLineService(TrainLinePersistence trainLinePersistence, TrainStationPersistence trainStationPersistence) {
        this.trainLinePersistence = trainLinePersistence;
        this.trainStationPersistence = trainStationPersistence;
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

    public SumTicketPrices findTicketPricesSumByLineColor(String color) {
        return new SumTicketPrices(this.trainStationPersistence.findAll()
                .filter(trainStation -> trainStation.getTrainLines().stream()
                        .anyMatch(trainLine -> color.equalsIgnoreCase(trainLine.getColor())))
                .map(TrainStation::getZone)
                .distinct()
                .map(Zone::getTicketPrice)
                .filter(Objects::nonNull)
                .reduce(BigDecimal.ZERO, BigDecimal::add));
    }
    }

