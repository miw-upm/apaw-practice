package es.upm.miw.apaw_practice.domain.services.university;

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

    public void update(String email, Student student) {
        studentPersistence.update(email, student);
    }

    public boolean existsEmail(String email) {
        return studentPersistence.existEmail(email);
    }

}
