package es.upm.miw.apaw_practice.adapters.mongodb.university.persistence;

import es.upm.miw.apaw_practice.adapters.mongodb.university.daos.DegreeRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.university.entities.DegreeEntity;
import es.upm.miw.apaw_practice.domain.persistence_ports.university.DegreePersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.stream.Stream;

@Repository("degreePersistence")
public class DegreePersistenceMongodb implements DegreePersistence {

    private final DegreeRepository degreeRepository;

    @Autowired
    public DegreePersistenceMongodb(DegreeRepository degreeRepository) {
        this.degreeRepository = degreeRepository;
    }

    @Override
    public Stream<String> readAllTitles() {
        return this.degreeRepository.findAll().stream()
                .map(DegreeEntity::getTitle);
    }
}
