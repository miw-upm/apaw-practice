package es.upm.miw.apaw_practice.adapters.mongodb.university.persistence;

import es.upm.miw.apaw_practice.adapters.mongodb.university.daos.ClassroomRepository;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.models.university.Classroom;
import es.upm.miw.apaw_practice.domain.persistence_ports.university.ClassroomPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("ClassroomPersistence")
public class ClassroomPersistenceMongodb implements ClassroomPersistence {

    private final ClassroomRepository classroomRepository;

    @Autowired
    public ClassroomPersistenceMongodb(ClassroomRepository classroomRepository) {
        this.classroomRepository = classroomRepository;
    }

    @Override
    public Classroom readBySchoolAndNumber(String school, Integer number) {
        return this.classroomRepository
                .findBySchoolAndNumber(school, number)
                .orElseThrow(() -> new NotFoundException("Classroom school and number: " + school + ";" + number))
                .toClassroom();
    }

    @Override
    public void delete(String school, Integer number) {
        this.classroomRepository.deleteBySchoolAndNumber(school, number);
    }
}
