package es.upm.miw.apaw_practice.adapters.mongodb.Class;

import es.upm.miw.apaw_practice.adapters.mongodb.Class.daos.ClassRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.Class.daos.CourseRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.Class.daos.LearnerRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.Class.daos.ProfessorRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.Class.entities.ClassEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.Class.entities.CourseEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.Class.entities.LearnerEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.Class.entities.ProfessorEntity;
import es.upm.miw.apaw_practice.domain.models.Class.Class;
import es.upm.miw.apaw_practice.domain.models.Class.Learner;
import es.upm.miw.apaw_practice.domain.models.Class.Professor;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ClassSeederService {

    @Autowired
    private ClassRepository classRepository;
    @Autowired
    private LearnerRepository learnerRepository;
    @Autowired
    private ProfessorRepository professorRepository;
    @Autowired
    private CourseRepository courseRepository;

    public void seedDatabase() {
        LogManager.getLogger(this.getClass()).warn("------- Hotel Initial Load -----------");

        ClassEntity[] classEntities = {
                new ClassEntity("class1",60, LocalDate.of(2021,11,11)),
                new ClassEntity("class2",120,LocalDate.of(2021,9,6)),
                new ClassEntity("class3",180,LocalDate.of(2021,10,10))
        };

        this.classRepository.saveAll(Arrays.asList(classEntities));

        LearnerEntity[] learnerEntities = {
                new LearnerEntity("wang",22,false),
                new LearnerEntity("andres",25,false),
                new LearnerEntity("jesus",21,true)
        };

        this.learnerRepository.saveAll(Arrays.asList(learnerEntities));

        ProfessorEntity[] professorEntities = {
                new ProfessorEntity("eva","espanol",59,LocalDate.of(2011,9,11)),
                new ProfessorEntity("jose","china",55,LocalDate.of(2009,11,11)),
                new ProfessorEntity("nick","web",39,LocalDate.of(2019,1,1))
        };

        this.professorRepository.saveAll(Arrays.asList(professorEntities));

        CourseEntity[] courseEntities = {
                new CourseEntity("espanol",6,20),
                new CourseEntity("web",8,15),
                new CourseEntity("china",4,8)
        };

        this.courseRepository.saveAll(Arrays.asList(courseEntities));
    }

    public void deleteAll() {
        this.classRepository.deleteAll();
        this.learnerRepository.deleteAll();
        this.professorRepository.deleteAll();
        this.courseRepository.deleteAll();
    }
}
