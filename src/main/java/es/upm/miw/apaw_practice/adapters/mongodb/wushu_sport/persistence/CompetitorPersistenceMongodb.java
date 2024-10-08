package es.upm.miw.apaw_practice.adapters.mongodb.wushu_sport.persistence;

import es.upm.miw.apaw_practice.adapters.mongodb.wushu_sport.daos.CompetitorRepository;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.models.wuhshu_sport.Competitor;
import es.upm.miw.apaw_practice.domain.persistence_ports.wushu_sport.CompetitorPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("CompetitorPersistence")
public class CompetitorPersistenceMongodb implements CompetitorPersistence {

    private final CompetitorRepository competitorRepository;

    @Autowired
    public CompetitorPersistenceMongodb(CompetitorRepository competitorRepository) {
        this.competitorRepository = competitorRepository;
    }

    @Override
    public Competitor readByLicence(String licence) {
        return this.competitorRepository.findByLicence(licence)
                .orElseThrow(() -> new NotFoundException("Competitor with licence " + licence + " not found"))
                .toCompetitor();
    }
}
