package es.upm.miw.apaw_practice.adapters.mongodb.university.persistence;

import es.upm.miw.apaw_practice.adapters.mongodb.university.daos.UniversityRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.university.entities.UniversityEntity;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.models.university.University;
import es.upm.miw.apaw_practice.domain.persistence_ports.university.UniversityPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.stream.Stream;

@Repository("universityPersistance")
public class UniversityPersistenceMongodb implements UniversityPersistence {

    private final UniversityRepository universityRepository;

    @Autowired
    public UniversityPersistenceMongodb(UniversityRepository universityRepository) {
        this.universityRepository = universityRepository;
    }

    @Override
    public Stream<University> readAll() {
        return universityRepository
                .findAll()
                .stream()
                .map(UniversityEntity::toUniversity);
    }

    @Override
    public University create(University university) {
        return universityRepository
                .save(new UniversityEntity(university))
                .toUniversity();
    }

    @Override
    public University update(String topDomain, University university) {
        UniversityEntity universityEntity = universityRepository
                .findByTopDomain(topDomain)
                .orElseThrow(() -> new NotFoundException("University topDomain: " + topDomain));
        universityEntity.fromUniversity(university);
        return universityRepository
                .save(universityEntity)
                .toUniversity();
    }

    @Override
    public University read(String topDomain) {
        return universityRepository
                .findByTopDomain(topDomain)
                .orElseThrow(() -> new NotFoundException("University topDomain: " + topDomain))
                .toUniversity();
    }

    @Override
    public boolean existTopDomain(String topDomain) {
        return universityRepository
                .findByTopDomain(topDomain)
                .isPresent();
    }
}
