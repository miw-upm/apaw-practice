package es.upm.miw.apaw_practice.adapters.mongodb.university.persistence;

import es.upm.miw.apaw_practice.adapters.mongodb.university.daos.StudentRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.university.entities.StudentEntity;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.models.university.Student;
import es.upm.miw.apaw_practice.domain.persistence_ports.university.StudentPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("studentPersistence")
public class StudentPersistenceMongodb implements StudentPersistence {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentPersistenceMongodb(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public Student create(Student student) {
        return this.studentRepository
                .save(new StudentEntity(student))
                .toStudent();
    }

    @Override
    public boolean existDni(String dni) {
        return this.studentRepository
                .findByDni(dni)
                .isPresent();
    }

    @Override
    public Student readByDni(String dni) {
        return this.studentRepository
                .findByDni(dni)
                .orElseThrow(() -> new NotFoundException("Student dni: " + dni))
                .toStudent();
    }
}
