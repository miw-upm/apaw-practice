package es.upm.miw.apaw_practice.adapters.mongodb.university;

import es.upm.miw.apaw_practice.adapters.mongodb.university.daos.ClassroomRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.university.daos.DegreeRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.university.daos.StudentRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.university.daos.SubjectRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.university.entities.ClassroomEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.university.entities.DegreeEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.university.entities.StudentEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.university.entities.SubjectEntity;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class UniversitySeederService {

    @Autowired
    private DegreeRepository degreeRepository;
    @Autowired
    private SubjectRepository subjectRepository;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private ClassroomRepository classroomRepository;

    public void seedDatabase() {
        LogManager.getLogger(this.getClass()).warn("------- University Initial Load -----------");
        DegreeEntity[] degrees = {

        };
        this.degreeRepository.saveAll(Arrays.asList(degrees));
        SubjectEntity[] subjects = {

        };
        this.subjectRepository.saveAll(Arrays.asList(subjects));
        StudentEntity[] students = {

        };
        this.studentRepository.saveAll(Arrays.asList(students));
        ClassroomEntity[] classrooms = {

        };
        this.classroomRepository.saveAll(Arrays.asList(classrooms));
    }

    public void deleteAll() {
        this.degreeRepository.deleteAll();
        this.subjectRepository.deleteAll();
        this.studentRepository.deleteAll();
        this.classroomRepository.deleteAll();
    }
}
