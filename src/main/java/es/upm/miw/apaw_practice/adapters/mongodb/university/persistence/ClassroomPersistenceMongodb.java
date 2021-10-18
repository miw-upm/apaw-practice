package es.upm.miw.apaw_practice.adapters.mongodb.university.persistence;

import es.upm.miw.apaw_practice.adapters.mongodb.university.daos.ClassroomRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.university.daos.StudentRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.university.entities.StudentEntity;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.models.university.Classroom;
import es.upm.miw.apaw_practice.domain.persistence_ports.university.ClassroomPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.stream.IntStream;

@Repository("ClassroomPersistence")
public class ClassroomPersistenceMongodb implements ClassroomPersistence {

    private final ClassroomRepository classroomRepository;

    private final StudentRepository studentRepository;

    @Autowired
    public ClassroomPersistenceMongodb(ClassroomRepository classroomRepository, StudentRepository studentRepository) {
        this.classroomRepository = classroomRepository;
        this.studentRepository = studentRepository;
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

    @Override
    public Integer findCapacitySumByStudentDni(String studentDni) {
        StudentEntity studentEntity = this.studentRepository
                .findByDni(studentDni)
                .orElseThrow(() -> new NotFoundException("Student DNI: " + studentDni));

        return studentEntity.getSubjects().stream()
                .flatMapToInt(subjectEntity -> IntStream.of(
                        subjectEntity.getClassroom().getCapacity()))
                .sum();
    }
}
