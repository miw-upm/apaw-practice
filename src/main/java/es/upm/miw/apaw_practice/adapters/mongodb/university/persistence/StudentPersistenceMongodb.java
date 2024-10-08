package es.upm.miw.apaw_practice.adapters.mongodb.university.persistence;

import es.upm.miw.apaw_practice.adapters.mongodb.university.daos.DegreeRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.university.daos.StudentRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.university.entities.StudentEntity;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.models.university.Student;
import es.upm.miw.apaw_practice.domain.persistence_ports.university.StudentPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.stream.Stream;

@Repository("studentPersistance")
public class StudentPersistenceMongodb implements StudentPersistence {

    private final StudentRepository studentRepository;

    private final DegreeRepository degreeRepository;

    @Autowired
    public StudentPersistenceMongodb(StudentRepository studentRepository, DegreeRepository degreeRepository) {
        this.studentRepository = studentRepository;
        this.degreeRepository = degreeRepository;
    }

    @Override
    public Stream<Student> readAll() {
        return studentRepository
                .findAll()
                .stream()
                .map(StudentEntity::toStudent);
    }

    @Override
    public Student create(Student student) {
        return studentRepository
                .save(fixDegreeRelationship(new StudentEntity(student)))
                .toStudent();
    }

    @Override
    public Student update(String email, Student student) {
        StudentEntity studentEntity = studentRepository
                .findByEmail(email)
                .orElseThrow(() -> new NotFoundException("Student email: " + email));
        studentEntity.fromStudent(student);
        return studentRepository
                .save(fixDegreeRelationship(studentEntity))
                .toStudent();
    }

    @Override
    public Student read(String email) {
        return studentRepository
                .findByEmail(email)
                .orElseThrow(() -> new NotFoundException("Student email: " + email))
                .toStudent();
    }

    @Override
    public boolean existEmail(String email) {
        return studentRepository
                .findByEmail(email)
                .isPresent();
    }

    private StudentEntity fixDegreeRelationship(StudentEntity studentEntity) {
        studentEntity.setEnrolledDegrees(studentEntity
                .getEnrolledDegrees()
                .stream()
                .map(degree -> degreeRepository
                        .findByCode(degree.getCode())
                        .orElse(degree))
                .toList());
        return studentEntity;
    }
}
