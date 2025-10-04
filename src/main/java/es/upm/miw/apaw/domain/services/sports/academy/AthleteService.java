package es.upm.miw.apaw.domain.services.sports.academy;

import es.upm.miw.apaw.domain.models.UserDto;
import es.upm.miw.apaw.domain.models.sports.academy.Athlete;
import es.upm.miw.apaw.domain.persistenceports.sports.academy.IAthletePersistence;
import es.upm.miw.apaw.domain.restclients.UserRestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AthleteService {
    private final IAthletePersistence athletePersistence;
    private final UserRestClient userRestClient;

    @Autowired
    public AthleteService(
            IAthletePersistence athletePersistence,
            UserRestClient userRestClient){
        this.athletePersistence = athletePersistence;
        this.userRestClient = userRestClient;
    }

    public Athlete getById(UUID id) {
        var athlete = this.athletePersistence.getById(id);
        UserDto userDto = this.userRestClient.readById(id);
        athlete.setUser(userDto);
        return athlete;
    }
}
