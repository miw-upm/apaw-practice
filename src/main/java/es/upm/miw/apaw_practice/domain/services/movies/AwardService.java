package es.upm.miw.apaw_practice.domain.services.movies;

import es.upm.miw.apaw_practice.domain.persistence_ports.movies.AwardPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AwardService {

    private final AwardPersistence awardPersistence;

    @Autowired
    public AwardService(AwardPersistence awardPersistence) {
        this.awardPersistence = awardPersistence;
    }

    public void deleteAward(String nameCategoryYear) {
        this.awardPersistence.delete(nameCategoryYear);
    }
}
