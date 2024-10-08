package es.upm.miw.apaw_practice.adapters.mongodb.university.persistence;


import es.upm.miw.apaw_practice.adapters.mongodb.university.daos.TeacherRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.university.daos.UniversityRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.university.entities.TeacherEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.university.entities.UniversityEntity;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.models.university.Teacher;
import es.upm.miw.apaw_practice.domain.persistence_ports.university.TeacherPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.stream.Stream;

@Repository("teacherPersistance")
public class TeacherPersistenceMongodb implements TeacherPersistence {

    private final TeacherRepository teacherRepository;

    private final UniversityRepository universityRepository;

    @Autowired
    public TeacherPersistenceMongodb(TeacherRepository teacherRepository, UniversityRepository universityRepository) {
        this.teacherRepository = teacherRepository;
        this.universityRepository = universityRepository;
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
                .save(fixUniversityRelationship(new TeacherEntity(teacher)))
                .toTeacher();
    }

    @Override
    public Teacher update(String nationalId, Teacher teacher) {
        TeacherEntity teacherEntity = teacherRepository
                .findByNationalId(nationalId)
                .orElseThrow(() -> new NotFoundException("Teacher nationalId: " + nationalId));
        teacherEntity.fromTeacher(teacher);
        return teacherRepository
                .save(fixUniversityRelationship(teacherEntity))
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
    public void delete(String nationalId) {
        Optional<TeacherEntity> teacherToDelete = teacherRepository.findByNationalId(nationalId);
        teacherToDelete.ifPresent(teacherRepository::delete);
    }

    @Override
    public boolean existNationalId(String nationalId) {
        return teacherRepository
                .findByNationalId(nationalId)
                .isPresent();
    }

    private TeacherEntity fixUniversityRelationship(TeacherEntity teacherEntity) {
        UniversityEntity universityEntity = teacherEntity.getWorkplace();
        teacherEntity.setWorkplace(universityRepository.findByTopDomain(universityEntity.getTopDomain()).orElse(universityEntity));
        return teacherEntity;
    }
}
