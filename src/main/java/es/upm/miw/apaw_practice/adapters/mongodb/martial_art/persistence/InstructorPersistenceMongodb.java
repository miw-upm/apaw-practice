package es.upm.miw.apaw_practice.adapters.mongodb.martial_art.persistence;

import es.upm.miw.apaw_practice.adapters.mongodb.martial_art.entities.InstructorEntity;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.models.martial_art.Instructor;
import es.upm.miw.apaw_practice.domain.persistence_ports.martial_art.InstructorPersistence;
import es.upm.miw.apaw_practice.adapters.mongodb.martial_art.daos.InstructorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.stream.Stream;


@Repository("instructorPersistence")
public class InstructorPersistenceMongodb implements InstructorPersistence {

    private final InstructorRepository instructorRepository;

    @Autowired
    public InstructorPersistenceMongodb(InstructorRepository instructorRepository) {
        this.instructorRepository = instructorRepository;
    }

    @Override
    public Stream<Instructor> readAll() {
        return this.instructorRepository
                .findAll().stream()
                .map(InstructorEntity::toInstructor);
    }

    @Override
    public Instructor create(Instructor instructor) {
        return this.instructorRepository
                .save(new InstructorEntity(instructor.getDni(), instructor.getFullName(), instructor.getBirthDate(),instructor.getFullName()))
                .toInstructor();

    }

    @Override
    public Instructor update(String dni, Instructor instructor) {
        InstructorEntity instructorEntity = this.instructorRepository
                .findByDni(dni)
                .orElseThrow(() -> new NotFoundException("Instructor dni: " + dni));
    return this.instructorRepository
                .save(instructorEntity)
                .toInstructor();
    }

    @Override
    public Instructor read(String dni) {
        return this.instructorRepository
                .findByDni(dni)
                .orElseThrow(() -> new NotFoundException("Instructor dni: " + dni))
                .toInstructor();
    }

    @Override
    public boolean existsByDni(String dni) {
        return this.instructorRepository
                .findByDni(dni)
                .isPresent();
    }

    @Override
    public void delete(String dni) {
        this.instructorRepository.deleteByDni(dni);

    }
}
