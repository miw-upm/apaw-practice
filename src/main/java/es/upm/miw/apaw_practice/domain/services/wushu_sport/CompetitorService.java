package es.upm.miw.apaw_practice.domain.services.wushu_sport;

import es.upm.miw.apaw_practice.domain.models.wuhshu_sport.Competitor;
import es.upm.miw.apaw_practice.domain.persistence_ports.wushu_sport.CompetitorPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompetitorService {

    private final CompetitorPersistence competitorPersistence;

    @Autowired
    public CompetitorService(CompetitorPersistence competitorPersistence) {
        this.competitorPersistence = competitorPersistence;
    }

    public Competitor readByLicence(String licence){
        return this.competitorPersistence.readByLicence(licence);
    }

}
