package es.upm.miw.apaw_practice.domain.persistence_ports.wushu_sport;

import es.upm.miw.apaw_practice.domain.models.wuhshu_sport.WushuSchool;
import org.springframework.stereotype.Repository;

import java.util.stream.Stream;

@Repository
public interface WushuSchoolPersistence {

    WushuSchool update (WushuSchool wushuSchool);
    WushuSchool readByName (String name);
    Stream<WushuSchool> readAll ();


}
