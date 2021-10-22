package es.upm.miw.apaw_practice.adapters.mongodb.football.persistence;

import es.upm.miw.apaw_practice.adapters.mongodb.football.daos.PrincipalRefereeRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.football.entities.PrincipalRefereeEntity;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.models.football.PrincipalReferee;
import es.upm.miw.apaw_practice.domain.persistence_ports.football.PrincipalRefereePersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("principalRefereePersistence")
public class PrincipalRefereePersistenceMongodb implements PrincipalRefereePersistence {

    private final PrincipalRefereeRepository principalRefereeRepository;

    @Autowired
    public PrincipalRefereePersistenceMongodb(PrincipalRefereeRepository principalRefereeRepository) {
        this.principalRefereeRepository = principalRefereeRepository;
    }

    @Override
    public PrincipalReferee create(PrincipalReferee principalReferee) {
        return this.principalRefereeRepository
                .save(new PrincipalRefereeEntity(principalReferee))
                .toPrincipalReferee();
    }

    @Override
    public PrincipalReferee readByName(String q) {
        return this.principalRefereeRepository.findByName(q)
                .orElseThrow(() -> new NotFoundException("PrincipalReferee with name: " + q))
                .toPrincipalReferee();
    }
}
