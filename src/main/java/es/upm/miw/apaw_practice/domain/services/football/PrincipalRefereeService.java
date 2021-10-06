package es.upm.miw.apaw_practice.domain.services.football;

import es.upm.miw.apaw_practice.domain.models.football.PrincipalReferee;
import es.upm.miw.apaw_practice.domain.persistence_ports.football.PrincipalRefereePersistence;
import org.springframework.stereotype.Service;

@Service
public class PrincipalRefereeService {
    private final PrincipalRefereePersistence principalRefereePersistence;

    public PrincipalRefereeService(PrincipalRefereePersistence principalRefereePersistence) {
        this.principalRefereePersistence = principalRefereePersistence;
    }

    public PrincipalReferee create(PrincipalReferee principalReferee) {
        return this.principalRefereePersistence.create(principalReferee);
    }
}
