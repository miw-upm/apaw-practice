package es.upm.miw.apaw.domain.services.metro;

import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.models.UserDto;
import es.upm.miw.apaw.domain.models.metro.Train;
import es.upm.miw.apaw.domain.restclients.UserRestClient;
import org.springframework.beans.factory.annotation.Autowired;
import es.upm.miw.apaw.domain.persistenceports.metro.TrainStationPersistence;
import org.springframework.stereotype.Service;

import java.util.stream.Stream;


@Service
public class TrainStationService {

    private final TrainStationPersistence trainStationPersistence;
    private final UserRestClient userRestClient;

    @Autowired
    public TrainStationService(TrainStationPersistence trainStationPersistence, UserRestClient userRestClient) {
        this.trainStationPersistence = trainStationPersistence;
        this.userRestClient = userRestClient;
    }

    public Integer readCapacityByName (String name) {
        return this.trainStationPersistence.readCapacityByName(name);
    }

    public Stream<Integer> findNumCarsByUserMobile(String mobile) {
        UserDto userDto = this.userRestClient.readByMobile(mobile);
        if (userDto == null) {
            throw new NotFoundException("User not found with mobile: " + mobile);
        }

        return this.trainStationPersistence.findAll()
                .filter(station -> station.getUsers() != null &&
                        station.getUsers().stream()
                                .anyMatch(u -> u.getId().equals(userDto.getId())))
                .flatMap(station -> station.getTrainLines().stream())
                .flatMap(line -> line.getTrains().stream())
                .map(Train::getNumCars)
                .distinct();
    }
}