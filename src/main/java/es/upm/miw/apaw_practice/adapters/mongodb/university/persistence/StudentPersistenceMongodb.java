package es.upm.miw.apaw_practice.adapters.mongodb.university.persistence;

import es.upm.miw.apaw_practice.adapters.mongodb.university.daos.StudentRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.university.daos.SubjectRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.university.entities.StudentEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.university.entities.SubjectEntity;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.models.university.Student;
import es.upm.miw.apaw_practice.domain.persistence_ports.university.StudentPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Repository("studentPersistence")
public class StudentPersistenceMongodb implements StudentPersistence {

    private final StudentRepository studentRepository;
    private final SubjectRepository subjectRepository;

    @Autowired
    public StudentPersistenceMongodb(StudentRepository studentRepository, SubjectRepository subjectRepository) {
        this.studentRepository = studentRepository;
        this.subjectRepository = subjectRepository;
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

    @Override
    public Stream<Student> findStudentsByClassroomSchool(String classroomSchool) {
        List<Integer> subjectEntityList = this.subjectRepository.findAll().stream()
                .filter(subjectEntity -> subjectEntity.getClassroom()
                        .getSchool().equals(classroomSchool))
                .map(SubjectEntity::getReference)
                .collect(Collectors.toList());

        return this.studentRepository.findAll().stream()
                .filter(studentEntity -> studentEntity.getSubjects().stream()
                        .anyMatch(subjectEntity -> subjectEntityList.contains(subjectEntity.getReference())))
                .map(StudentEntity::toStudent);
    }
}
