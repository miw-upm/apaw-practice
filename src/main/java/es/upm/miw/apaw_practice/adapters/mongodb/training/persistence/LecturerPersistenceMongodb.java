package es.upm.miw.apaw_practice.adapters.mongodb.training.persistence;

import es.upm.miw.apaw_practice.adapters.mongodb.training.daos.LecturerRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.training.entities.LecturerEntity;
import es.upm.miw.apaw_practice.domain.models.training.Lecturer;
import es.upm.miw.apaw_practice.domain.persistence_ports.training.LecturerPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("LecturerPersistence")
public class LecturerPersistenceMongodb implements LecturerPersistence {
    private final LecturerRepository lecturerRepository;

    @Autowired
    public LecturerPersistenceMongodb(LecturerRepository lecturerRepository) {
        this.lecturerRepository = lecturerRepository;
    }

    @Override
    public Lecturer create(Lecturer lecturer) {
        return this.lecturerRepository
                .save(new LecturerEntity(lecturer))
                .toLecturer();
    }
}
