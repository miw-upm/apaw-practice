package es.upm.miw.apaw_practice.domain.persistence_ports.wushu_sport;

import es.upm.miw.apaw_practice.domain.models.wuhshu_sport.Competitor;
import org.springframework.stereotype.Repository;

@Repository
public interface CompetitorPersistence {

    Competitor readByLicence(String licence);

}
