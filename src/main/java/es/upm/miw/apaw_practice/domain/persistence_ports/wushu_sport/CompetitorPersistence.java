package es.upm.miw.apaw_practice.domain.persistence_ports.wushu_sport;

import es.upm.miw.apaw_practice.domain.models.wuhshu_sport.Competitor;
import org.springframework.stereotype.Repository;

import java.util.stream.Stream;

@Repository
public interface CompetitorPersistence {

    Stream<Competitor> readAll();

    Competitor readByLicence(String licence);

}
