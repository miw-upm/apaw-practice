package es.upm.miw.apaw_practice.domain.services.university;

import es.upm.miw.apaw_practice.domain.persistence_ports.university.TeacherPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeacherService {

    private final TeacherPersistence teacherPersistence;

    @Autowired
    public TeacherService(TeacherPersistence teacherPersistence) {
        this.teacherPersistence = teacherPersistence;
    }

    public void delete(String nationalId) {
        teacherPersistence.delete(nationalId);
    }

}
