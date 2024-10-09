package es.upm.miw.apaw_practice.domain.persistence_ports.wushu_sport;

import es.upm.miw.apaw_practice.domain.models.wuhshu_sport.CompetitionForm;
import org.springframework.stereotype.Repository;

import java.util.stream.Stream;

@Repository
public interface CompetitionFormPersistence {

    CompetitionForm create (CompetitionForm competitionForm);

    Stream<CompetitionForm> readAll();
}
