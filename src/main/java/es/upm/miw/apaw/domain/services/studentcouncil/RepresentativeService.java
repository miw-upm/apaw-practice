package es.upm.miw.apaw.domain.services.studentcouncil;

import es.upm.miw.apaw.domain.models.UserDto;
import es.upm.miw.apaw.domain.models.studentcouncil.Representative;
import es.upm.miw.apaw.domain.persistenceports.studentcouncil.RepresentativePersistence;
import es.upm.miw.apaw.domain.restclients.UserRestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class RepresentativeService {

    private final RepresentativePersistence representativePersistence;
    private final UserRestClient userRestClient;

    @Autowired
    public RepresentativeService(RepresentativePersistence representativePersistence,
                                 UserRestClient userRestClient) {
        this.representativePersistence = representativePersistence;
        this.userRestClient = userRestClient;
    }

    public List<Representative> getAllRepresentatives() {
        return this.representativePersistence.readAll()
                .peek(rep -> {
                    UUID userId = rep.getRepresentative().getId();
                    if (userId != null) {
                        UserDto fullUser = userRestClient.readById(userId);
                        rep.setRepresentative(fullUser);
                    }
                })
                .toList();
    }
}