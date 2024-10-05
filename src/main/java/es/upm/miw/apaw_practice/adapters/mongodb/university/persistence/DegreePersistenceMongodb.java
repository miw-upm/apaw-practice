package es.upm.miw.apaw_practice.adapters.mongodb.university.persistence;

import es.upm.miw.apaw_practice.adapters.mongodb.university.daos.DegreeRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.university.entities.DegreeEntity;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.models.university.Degree;
import es.upm.miw.apaw_practice.domain.persistence_ports.university.DegreePersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.stream.Stream;

@Repository("degreePersistance")
public class DegreePersistenceMongodb implements DegreePersistence {

    private final DegreeRepository degreeRepository;

    @Autowired
    public DegreePersistenceMongodb(DegreeRepository degreeRepository) {
        this.degreeRepository = degreeRepository;
    }

    @Override
    public Stream<Degree> readAll() {
        return degreeRepository
                .findAll()
                .stream()
                .map(DegreeEntity::toDegree);
    }

    @Override
    public Degree create(Degree degree) {
        return degreeRepository
                .save(new DegreeEntity(degree))
                .toDegree();
    }

    @Override
    public Degree update(Integer code, Degree degree) {
        DegreeEntity degreeEntity = degreeRepository
                .findByCode(code)
                .orElseThrow(() -> new NotFoundException("Degree code: " + code.toString()));
        degreeEntity.fromDegree(degree);
        return degreeRepository
                .save(degreeEntity)
                .toDegree();
    }

    @Override
    public Degree read(Integer code) {
        return degreeRepository
                .findByCode(code)
                .orElseThrow(() -> new NotFoundException("Degree code: " + code.toString()))
                .toDegree();
    }

    @Override
    public boolean existCode(Integer code) {
        return degreeRepository
                .findByCode(code)
                .isPresent();
    }
}
