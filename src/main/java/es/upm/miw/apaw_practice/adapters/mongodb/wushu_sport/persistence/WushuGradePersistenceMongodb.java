package es.upm.miw.apaw_practice.adapters.mongodb.wushu_sport.persistence;

import es.upm.miw.apaw_practice.adapters.mongodb.wushu_sport.daos.WushuGradeRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.wushu_sport.entities.WushuGradeEntity;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.models.wuhshu_sport.WushuGrade;
import es.upm.miw.apaw_practice.domain.persistence_ports.wushu_sport.WushuGradePersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("wushuGradePersistence")
public class WushuGradePersistenceMongodb implements WushuGradePersistence {

    private final WushuGradeRepository wushuGradeRepository;

    @Autowired
    public WushuGradePersistenceMongodb(WushuGradeRepository wushuGradeRepository){
        this.wushuGradeRepository = wushuGradeRepository;
    }

    @Override
    public void delete(String gradeTitle){
        this.wushuGradeRepository.deleteByGradeTitle(gradeTitle);
    }

    @Override
    public WushuGrade readByGradeTitle(String gradeTitle){
        return this.wushuGradeRepository.findByGradeTitle(gradeTitle)
                .orElseThrow(() -> new NotFoundException("WushuGrade with title " + gradeTitle))
                .toWushuGrade();
    }
    @Override
    public WushuGrade update(String gradeTitle, WushuGrade wushuGrade){
        WushuGradeEntity wushuGradeEntity = this.wushuGradeRepository
                .findByGradeTitle(gradeTitle)
                .orElseThrow(() -> new NotFoundException("WushuGrade with title " + gradeTitle));
        wushuGradeEntity.fromWushuGrade(wushuGrade);
        return this.wushuGradeRepository.save(wushuGradeEntity).toWushuGrade();
    }
}
