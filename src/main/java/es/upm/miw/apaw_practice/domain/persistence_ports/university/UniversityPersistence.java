package es.upm.miw.apaw_practice.domain.persistence_ports.university;

import es.upm.miw.apaw_practice.domain.models.university.University;
import org.springframework.stereotype.Repository;

import java.util.stream.Stream;

@Repository
public interface UniversityPersistence {

    Stream<University> readAll();

    University create(University university);

    University update(String topDomain, University university);

    University read(String topDomain);

    boolean existTopDomain(String topDomain);

}
