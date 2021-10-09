package es.upm.miw.apaw_practice.domain.services.football;

import es.upm.miw.apaw_practice.domain.models.football.Stadium;
import es.upm.miw.apaw_practice.domain.persistence_ports.football.StadiumPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StadiumService {

    private final StadiumPersistence stadiumPersistence;

    @Autowired
    public StadiumService(StadiumPersistence stadiumPersistence) {
        this.stadiumPersistence = stadiumPersistence;
    }

    public void updateName(String city, Stadium stadium) {
        Stadium stadiumResult = this.stadiumPersistence.readByCity(city);
        stadiumResult.setName(stadium.getName());
        this.stadiumPersistence.update(stadiumResult);
    }
}
