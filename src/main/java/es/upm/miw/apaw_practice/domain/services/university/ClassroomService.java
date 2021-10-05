package es.upm.miw.apaw_practice.domain.services.university;

import es.upm.miw.apaw_practice.domain.persistence_ports.university.ClassroomPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClassroomService {

    private final ClassroomPersistence classroomPersistence;

    @Autowired
    public ClassroomService(ClassroomPersistence classroomPersistence) {
        this.classroomPersistence = classroomPersistence;
    }

    public void delete(String school, Integer number) {
        this.classroomPersistence.delete(school, number);
    }
}
