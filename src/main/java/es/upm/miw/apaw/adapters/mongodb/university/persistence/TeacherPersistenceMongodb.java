package es.upm.miw.apaw.adapters.mongodb.university.persistence;

import es.upm.miw.apaw.adapters.mongodb.university.daos.TeacherRepository;
import es.upm.miw.apaw.adapters.mongodb.university.entities.TeacherEntity;
import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.models.university.Teacher;
import es.upm.miw.apaw.domain.persistenceports.university.TeacherPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository("teacherPersistence")
public class TeacherPersistenceMongodb implements TeacherPersistence {
    private final TeacherRepository teacherRepository;

    @Autowired
    public TeacherPersistenceMongodb(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    @Override
    public Teacher getById(UUID id) {
        return this.teacherRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException("Teacher not found with id: " + id))
                .toTeacher();
    }

    @Override
    public Teacher update(UUID id, Teacher teacher) {
        return this.teacherRepository
                .save(new TeacherEntity(id, teacher))
                .toTeacher();
    }

    @Override
    public boolean existIdentificationCode(String identificationCode) {
        return this.teacherRepository
                .findByIdentificationCode(identificationCode)
                .isPresent();
    }
}

