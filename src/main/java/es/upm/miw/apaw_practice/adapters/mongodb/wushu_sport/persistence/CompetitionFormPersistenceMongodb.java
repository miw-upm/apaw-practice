package es.upm.miw.apaw_practice.adapters.mongodb.wushu_sport.persistence;

import es.upm.miw.apaw_practice.adapters.mongodb.wushu_sport.daos.CompetitionFormRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.wushu_sport.entities.CompetitionFormEntity;
import es.upm.miw.apaw_practice.domain.models.wuhshu_sport.CompetitionForm;
import es.upm.miw.apaw_practice.domain.persistence_ports.wushu_sport.CompetitionFormPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.stream.Stream;

@Repository("competitionFormPersistence")
public class CompetitionFormPersistenceMongodb implements CompetitionFormPersistence {

    private final CompetitionFormRepository competitionFormRepository;

    @Autowired
    public CompetitionFormPersistenceMongodb(CompetitionFormRepository competitionFormRepository) {
        this.competitionFormRepository = competitionFormRepository;
    }
    @Override
    public CompetitionForm create(CompetitionForm competitionForm) {
        return this.competitionFormRepository
                .save(new CompetitionFormEntity(competitionForm))
                .toCompetitionForm();
    }

    @Override
    public Stream<CompetitionForm> readAll() {
        return this.competitionFormRepository.findAll().stream()
                .map(CompetitionFormEntity::toCompetitionForm);
    }
}