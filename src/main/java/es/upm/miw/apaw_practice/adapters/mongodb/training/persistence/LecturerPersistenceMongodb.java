package es.upm.miw.apaw_practice.adapters.mongodb.training.persistence;

import es.upm.miw.apaw_practice.adapters.mongodb.training.daos.LecturerRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.training.entities.LecturerEntity;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.models.training.Lecturer;
import es.upm.miw.apaw_practice.domain.persistence_ports.training.LecturerPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.stream.Stream;

@Repository("LecturerPersistence")
public class LecturerPersistenceMongodb implements LecturerPersistence {
    private final LecturerRepository lecturerRepository;

    @Autowired
    public LecturerPersistenceMongodb(LecturerRepository lecturerRepository) {
        this.lecturerRepository = lecturerRepository;
    }

    @Override
    public Lecturer readByDni(String dni) {
        return this.lecturerRepository.findByDni(dni)
                .orElseThrow(() -> new NotFoundException("Lecturer dni: " + dni))
                .toLecturer();
    }

    @Override
    public Stream<Lecturer> readAll() {
        return this.lecturerRepository.findAll().stream()
                .map(LecturerEntity::toLecturer);
    }

    @Override
    public Lecturer update(Lecturer lecturer) {
        LecturerEntity lecturerEntity = this.lecturerRepository
                .findByDni(lecturer.getDni())
                .orElseThrow(() -> new NotFoundException("Lecturer dni: " + lecturer.getDni()));
        lecturerEntity.fromLecturer(lecturer);
        return lecturerRepository.save(lecturerEntity).toLecturer();
    }
}
