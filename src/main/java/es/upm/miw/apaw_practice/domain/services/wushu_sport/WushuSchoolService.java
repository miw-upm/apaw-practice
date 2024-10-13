package es.upm.miw.apaw_practice.domain.services.wushu_sport;

import es.upm.miw.apaw_practice.domain.models.wuhshu_sport.Competitor;
import es.upm.miw.apaw_practice.domain.models.wuhshu_sport.WushuSchool;
import es.upm.miw.apaw_practice.domain.persistence_ports.wushu_sport.WushuSchoolPersistence;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WushuSchoolService {

    private final WushuSchoolPersistence wushuSchoolPersistence;

    public WushuSchoolService(WushuSchoolPersistence wushuSchoolPersistence) {
        this.wushuSchoolPersistence = wushuSchoolPersistence;
    }

    public WushuSchool updateWushuSchool(String name, List<Competitor> competitorsList) {
        WushuSchool wushuSchool = this.wushuSchoolPersistence.readByName(name);
        wushuSchool.setCompetitors(competitorsList);
        return this.wushuSchoolPersistence.update(wushuSchool);
    }



}
