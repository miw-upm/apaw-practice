package es.upm.miw.apaw_practice.adapters.mongodb.university.persistence;

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

    @Autowired
    public StudentPersistenceMongodb(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
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
                .save(new StudentEntity(student))
                .toStudent();
    }

    @Override
    public Student update(String email, Student student) {
        StudentEntity studentEntity = studentRepository
                .findByEmail(email)
                .orElseThrow(() -> new NotFoundException("Student email: " + email));
        studentEntity.fromStudent(student);
        return studentRepository
                .save(studentEntity)
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
}
