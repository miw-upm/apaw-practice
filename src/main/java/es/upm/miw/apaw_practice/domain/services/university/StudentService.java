package es.upm.miw.apaw_practice.domain.services.university;

import es.upm.miw.apaw_practice.domain.exceptions.ConflictException;
import es.upm.miw.apaw_practice.domain.models.university.Student;
import es.upm.miw.apaw_practice.domain.persistence_ports.university.StudentPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    private final StudentPersistence studentPersistence;

    @Autowired
    public StudentService(StudentPersistence studentPersistence) {
        this.studentPersistence = studentPersistence;
    }

    public Student create(Student student) {
        this.assertDniNotExist(student.getDni());
        return this.studentPersistence.create(student);
    }

    public void assertDniNotExist(String dni) {
        if (this.studentPersistence.existDni(dni)) {
            throw new ConflictException("DNI exist: " + dni);
        }
    }

}
