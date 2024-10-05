package es.upm.miw.apaw_practice.adapters.mongodb.university.persistence;


import es.upm.miw.apaw_practice.adapters.mongodb.university.daos.TeacherRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.university.entities.TeacherEntity;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.models.university.Teacher;
import es.upm.miw.apaw_practice.domain.persistence_ports.university.TeacherPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.stream.Stream;

@Repository("TeacherPersistance")
public class TeacherPersistenceMongodb implements TeacherPersistence {

    private final TeacherRepository teacherRepository;

    @Autowired
    public TeacherPersistenceMongodb(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    @Override
    public Stream<Teacher> readAll() {
        return teacherRepository
                .findAll()
                .stream()
                .map(TeacherEntity::toTeacher);
    }

    @Override
    public Teacher create(Teacher teacher) {
        return teacherRepository
                .save(new TeacherEntity(teacher))
                .toTeacher();
    }

    @Override
    public Teacher update(String nationalId, Teacher teacher) {
        TeacherEntity teacherEntity = teacherRepository
                .findByNationalId(nationalId)
                .orElseThrow(() -> new NotFoundException("Teacher nationalId: " + nationalId));
        teacherEntity.fromTeacher(teacher);
        return teacherRepository
                .save(teacherEntity)
                .toTeacher();
    }

    @Override
    public Teacher read(String nationalId) {
        return teacherRepository
                .findByNationalId(nationalId)
                .orElseThrow(() -> new NotFoundException("Teacher nationalId: " + nationalId))
                .toTeacher();
    }

    @Override
    public boolean existNationalId(String nationalId) {
        return teacherRepository
                .findByNationalId(nationalId)
                .isPresent();
    }
}
