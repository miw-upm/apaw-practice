package es.upm.miw.apaw_practice.adapters.mongodb.padel_academy.persistence;

import es.upm.miw.apaw_practice.adapters.mongodb.padel_academy.daos.InstructorRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.padel_academy.entities.InstructorEntity;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.models.padel_academy.Instructor;
import es.upm.miw.apaw_practice.domain.persistence_ports.padel_academy.InstructorPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("customInstructorRepository")
public class InstructorPersistenceMongodb implements InstructorPersistence {
    private final InstructorRepository instructorRepository;
    @Autowired
    public InstructorPersistenceMongodb(InstructorRepository instructorRepository){
        this.instructorRepository = instructorRepository;
    }

    @Override
    public Instructor read(String dni) {
        return this.instructorRepository.findByDni(dni)
                .orElseThrow(() -> new NotFoundException("Instructor with dni " + dni + " not found."))
                .toInstructor();
    }

    @Override
    public Instructor update(Instructor instructor) {
        InstructorEntity instructorEntity = this.instructorRepository
                .findByDni(instructor.getDni())
                .orElseThrow(()-> new NotFoundException("Instructor with dni"+ instructor.getDni() + " not found"));
        instructorEntity.fromInstructor(instructor);
        return this.instructorRepository
                .save(instructorEntity)
                .toInstructor();
    }

    @Override
    public Instructor readByName(String name) {
        return this.instructorRepository.findByName(name)
                .orElseThrow(() -> new NotFoundException("Instructor with name " + name + " not found."))
                .toInstructor();
    }
}
