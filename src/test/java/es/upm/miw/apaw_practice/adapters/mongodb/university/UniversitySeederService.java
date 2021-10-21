package es.upm.miw.apaw_practice.adapters.mongodb.university;

import es.upm.miw.apaw_practice.adapters.mongodb.university.daos.ClassroomRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.university.daos.DegreeRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.university.daos.StudentRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.university.daos.SubjectRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.university.entities.ClassroomEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.university.entities.DegreeEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.university.entities.StudentEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.university.entities.SubjectEntity;
import es.upm.miw.apaw_practice.domain.models.university.Classroom;
import es.upm.miw.apaw_practice.domain.models.university.Student;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

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
        ClassroomEntity[] classrooms = {
                new ClassroomEntity(Classroom.builder().school("ETSISI").number(1302).capacity(20).build()),
                new ClassroomEntity(Classroom.builder().school("ETSISI").number(3101).capacity(40).build()),
                new ClassroomEntity(Classroom.builder().school("ETSISI").number(4302).capacity(30).build()),
                new ClassroomEntity(Classroom.builder().school("ETSISI").number(3004).capacity(10).build()),
                new ClassroomEntity(Classroom.builder().school("ETSISI").number(8002).capacity(15).build()),
                new ClassroomEntity(Classroom.builder().school("ETSIINF").number(9021).capacity(50).build())
        };
        this.classroomRepository.saveAll(Arrays.asList(classrooms));
        SubjectEntity[] subjects = {
                new SubjectEntity(613000096, "Arquitectura y Patrones para Aplicaciones Web", 4, classrooms[0]),
                new SubjectEntity(613000095, "Ingeniería Web: Visión General", 6, classrooms[0]),
                new SubjectEntity(615000246, "Inteligencia artificial", 3, classrooms[1]),
                new SubjectEntity(615000243, "Programación Orientada a Objetos", 6, classrooms[2]),
                new SubjectEntity(615000225, "Análisis matemático", 6, classrooms[0]),
                new SubjectEntity(615000232, "Fundamentos de Seguridad", 3, classrooms[0]),
                new SubjectEntity(105000002, "Lógica", 6, classrooms[5])
        };
        this.subjectRepository.saveAll(Arrays.asList(subjects));
        DegreeEntity[] degrees = {
                new DegreeEntity(4313004, "Máster en Ingeniería Web", LocalDate.parse("2011-09-01"), Arrays.asList(subjects)),
                new DegreeEntity(2503028, "Ingeniería del Software", LocalDate.parse("2014-09-01")),
                new DegreeEntity(2503027, "Ingeniería de Computadores", LocalDate.parse("2014-09-01")),
        };
        this.degreeRepository.saveAll(Arrays.asList(degrees));
        StudentEntity[] students = {
                new StudentEntity(new Student("12345678X", "Ada Lovelace", true), Arrays.asList(subjects)),
                new StudentEntity(new Student("112233445E", "Alan Turing", true), List.of(subjects[2], subjects[3])),
                new StudentEntity(new Student("999999999W", "Bárbara Liskov", false), List.of(subjects[6]))

        };
        this.studentRepository.saveAll(Arrays.asList(students));
    }

    public void deleteAll() {
        this.degreeRepository.deleteAll();
        this.subjectRepository.deleteAll();
        this.studentRepository.deleteAll();
        this.classroomRepository.deleteAll();
    }
}
