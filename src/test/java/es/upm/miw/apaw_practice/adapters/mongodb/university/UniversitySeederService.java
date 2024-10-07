package es.upm.miw.apaw_practice.adapters.mongodb.university;

import es.upm.miw.apaw_practice.adapters.mongodb.university.daos.DegreeRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.university.daos.StudentRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.university.daos.TeacherRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.university.daos.UniversityRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.university.entities.DegreeEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.university.entities.StudentEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.university.entities.TeacherEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.university.entities.UniversityEntity;
import es.upm.miw.apaw_practice.domain.models.university.Degree;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class UniversitySeederService {

    @Autowired
    private DegreeRepository degreeRepository;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private TeacherRepository teacherRepository;
    @Autowired
    private UniversityRepository universityRepository;

    public void seedDatabase() {
        LogManager.getLogger(this.getClass()).warn("------- University Initial Load -----------");
        DegreeEntity[] degrees = {
                new DegreeEntity(new Degree(2000, 30, "Fine Arts", "Program that explores artistic expression in painting, sculpture and digital media, developing creative skills and techniques for innovative visual projects.")),
                new DegreeEntity(new Degree(2001, 40, "Visual Arts", "Training focused on the analysis and creation of contemporary works, combining theory and practice in disciplines such as photography, video and graphic design.")),
                new DegreeEntity(new Degree(2002, 45, "Sociology", "Study of social dynamics, structures and human behavior, providing tools to analyze phenomena such as inequality, culture and social change.")),
                new DegreeEntity(new Degree(2003, 20, "Political Science", "Program that addresses the functioning of political systems, the theory of power and governance, preparing professionals to analyze and participate in public policies and electoral processes.")),
                new DegreeEntity(new Degree(2004, 15, "Political Science", "Training in political theory, government systems and international relations, focused on understanding power, institutions and decision-making at global and local levels.")),
        };
        degreeRepository.saveAll(Arrays.asList(degrees));
        StudentEntity[] students = {
                new StudentEntity("emily.johnson@example.org", "Emily", "London", LocalDate.of(2004, 4, 12),
                        new ArrayList<>()),
                new StudentEntity("james.miller@example.org", "James", "Manchester", LocalDate.of(1997, 4, 17),
                        List.of(degrees[1])),
                new StudentEntity("sophia.davis@example.org", "Sophia", "Birmingham", LocalDate.of(2002, 6, 3),
                        List.of(degrees[0], degrees[2], degrees[4])),
                new StudentEntity("michael.brown@example.org", "Michael", "Liverpool", LocalDate.of(1989, 1, 29),
                        List.of(degrees[1], degrees[2])),
                new StudentEntity("olivia.wilson@example.org", "Olivia", "Liverpool", LocalDate.of(1999, 9, 12),
                        List.of(degrees[0]))
        };
        studentRepository.saveAll(Arrays.asList(students));
        UniversityEntity[] universities = {
                new UniversityEntity("ox.ac.uk", "University of Oxford", true, 1,
                        new ArrayList<>()),
                new UniversityEntity("cam.ac.uk", "University of Cambridge", false, 3,
                        List.of(degrees[1], degrees[2], degrees[3])),
                new UniversityEntity("imperial.ac.uk", "Imperial College London", false, 7,
                        List.of(degrees[0], degrees[2])),
                new UniversityEntity("manchester.ac.uk", "University of Manchester", true, 2,
                        List.of(degrees[3])),
                new UniversityEntity("lse.ac.uk", "London School of Economics and Political Science", true, 3,
                        List.of(degrees[0], degrees[1]))
        };
        universityRepository.saveAll(Arrays.asList(universities));
        universityRepository.findByTopDomain("cam.ac.uk");
        TeacherEntity[] teachers = {
                new TeacherEntity("QQ123456A", LocalDate.of(1980, 6, 12), "Carter", null),
                new TeacherEntity("AB987654C", LocalDate.of(1976, 1, 2), "Anderson", universities[0]),
                new TeacherEntity("CD876543B", LocalDate.of(1976, 6, 26), "Matthews", universities[2]),
                new TeacherEntity("EF654321D", LocalDate.of(1991, 3, 23), "Thompson", universities[3]),
                new TeacherEntity("GH345678E", LocalDate.of(1984, 9, 2), "Roberts", universities[4])
        };
        teacherRepository.saveAll(Arrays.asList(teachers));
    }

    public void deleteAll() {
        degreeRepository.deleteAll();
        studentRepository.deleteAll();
        teacherRepository.deleteAll();
        universityRepository.deleteAll();
    }
}
