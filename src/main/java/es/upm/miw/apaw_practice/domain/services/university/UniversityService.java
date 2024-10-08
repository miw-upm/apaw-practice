package es.upm.miw.apaw_practice.domain.services.university;

import es.upm.miw.apaw_practice.domain.models.university.University;
import es.upm.miw.apaw_practice.domain.persistence_ports.university.UniversityPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UniversityService {

    private final UniversityPersistence universityPersistence;

    @Autowired
    public UniversityService(UniversityPersistence universityPersistence) {
        this.universityPersistence = universityPersistence;
    }

    public void create(University university) {
        universityPersistence.create(university);
    }

    public boolean existsTopDomain(String topDomain) {
        return universityPersistence.existTopDomain(topDomain);
    }

}
