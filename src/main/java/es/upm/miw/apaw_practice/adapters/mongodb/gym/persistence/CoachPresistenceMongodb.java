package es.upm.miw.apaw_practice.adapters.mongodb.gym.persistence;


import es.upm.miw.apaw_practice.adapters.mongodb.gym.daos.CoachRepository;
import es.upm.miw.apaw_practice.domain.persistence_ports.gym.CoachPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("CoachPersistence")
public class CoachPresistenceMongodb implements CoachPersistence {
    private final CoachRepository coachRepository;

    @Autowired
    public CoachPresistenceMongodb(CoachRepository coachRepository) {
        this.coachRepository = coachRepository;
    }


    @Override
    public void delete(String dni) {
        this.coachRepository.deleteByDni(dni);

    }
}
