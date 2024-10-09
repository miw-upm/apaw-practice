package es.upm.miw.apaw_practice.adapters.mongodb.wushu_sport.persistence;

import es.upm.miw.apaw_practice.adapters.mongodb.wushu_sport.daos.CompetitionFormRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.wushu_sport.daos.CompetitorRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.wushu_sport.daos.WushuGradeRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.wushu_sport.daos.WushuSchoolRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.wushu_sport.entities.CompetitionFormEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.wushu_sport.entities.CompetitorEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.wushu_sport.entities.WushuGradeEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.wushu_sport.entities.WushuSchoolEntity;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.models.wuhshu_sport.WushuSchool;
import es.upm.miw.apaw_practice.domain.persistence_ports.wushu_sport.WushuSchoolPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Repository("wushuSchoolPersistence")
public class WushuSchoolPersistenceMongodb implements WushuSchoolPersistence {

    private final WushuSchoolRepository wushuSchoolRepository;
    private final WushuGradeRepository wushuGradeRepository;
    private final CompetitorRepository competitorRepository;
    private final CompetitionFormRepository competitionFormRepository;

    @Autowired
    public WushuSchoolPersistenceMongodb(WushuSchoolRepository wushuSchoolRepository, WushuGradeRepository wushuGradeRepository, CompetitionFormRepository competitionFormRepository, CompetitionFormRepository competitionForm, CompetitorRepository competitorRepository) {
        this.wushuSchoolRepository = wushuSchoolRepository;
        this.wushuGradeRepository = wushuGradeRepository;
        this.competitionFormRepository = competitionFormRepository;
        this.competitorRepository = competitorRepository;
    }

    @Override
    public Stream<WushuSchool> readAll() {
        return this.wushuSchoolRepository.findAll().stream()
                .map(WushuSchoolEntity::toWushuSchool);
    }


    @Override
    public WushuSchool readByName(String name) {
        return this.wushuSchoolRepository.findByName(name)
                .orElseThrow(() -> new NotFoundException("WushuSchool with name " + name))
                .toWushuSchool();
    }

    @Override
    public WushuSchool update(WushuSchool wushuSchool) {
        WushuSchoolEntity wushuSchoolEntity = this.wushuSchoolRepository
                .findByName(wushuSchool.getName())
                .orElseThrow(() -> new NotFoundException("WushuSchool with name" + wushuSchool.getName()));

        List<CompetitorEntity> competitorsEntities = wushuSchool.getCompetitors().stream()
                .map(competitor ->
                        new  CompetitorEntity(
                                competitor.getLicence(),
                                competitor.getFederatedYears(),
                                competitor.getLastFederationDate(),
                                new WushuGradeEntity(competitor.getWushuGrade()),
                                competitor.getCompetitionForms().stream().map(
                                        CompetitionFormEntity::new
                                ).collect(Collectors.toList()))
                ).collect(Collectors.toList());
        for (CompetitorEntity competitorEntity : competitorsEntities) {
            wushuGradeRepository.save(competitorEntity.getWuhsuGradeEntity());
            competitionFormRepository.saveAll(competitorEntity.getCompetitionFormsEntities());
        }
        competitorRepository.saveAll(competitorsEntities);
        wushuSchoolEntity.setCompetitorsEntities(competitorsEntities);
        return this.wushuSchoolRepository.save(wushuSchoolEntity).toWushuSchool();
    }
}