package es.upm.miw.apaw_practice.adapters.mongodb.training;

import es.upm.miw.apaw_practice.adapters.mongodb.training.daos.CourseRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.training.daos.LecturerRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.training.daos.ParticipantRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.training.daos.TrainingItemRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.training.entities.CourseEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.training.entities.LecturerEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.training.entities.ParticipantEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.training.entities.TrainingItemEntity;
import es.upm.miw.apaw_practice.domain.models.training.Course;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Service
public class TrainingSeederService {

    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private ParticipantRepository participantRepository;
    @Autowired
    private TrainingItemRepository trainingItemRepository;
    @Autowired
    private LecturerRepository lecturerRepository;


    public void seedDatabase() {
        LogManager.getLogger(this.getClass()).warn("------- Training Initial Load -----------");

        CourseEntity[] courses = {
                new CourseEntity(new Course("62001",1, new BigDecimal("250.59"))),
                new CourseEntity(new Course("62002",2, new BigDecimal("288.68"))),
                new CourseEntity(new Course("62003",3, new BigDecimal("426.32")))
        };
        this.courseRepository.saveAll(Arrays.asList(courses));

        LecturerEntity[] lecturers = {
                new LecturerEntity("3386275R", LocalDate.of(1998,4,6),24),
                new LecturerEntity("1468048B", LocalDate.of(2005,10,13),17),
                new LecturerEntity("7732893D", LocalDate.of(2016,5,18),6),
                new LecturerEntity("7114831L", LocalDate.of(2008,9,11),20)
        };
        this.lecturerRepository.saveAll(Arrays.asList(lecturers));

        ParticipantEntity[] participants = {
                new ParticipantEntity("Luis", true,  874326783, "luis3215@gmail.com", List.of(courses[0], courses[1])),
                new ParticipantEntity("Mario", true,  523416211, "mario12@gmail.com", List.of(courses[1], courses[2])),
                new ParticipantEntity("Juan", false,  643167221, "juan99@gmail.com", List.of(courses[0], courses[2])),
                new ParticipantEntity("Sebastian", false,  655216879, "sebastian808@gmail.com", List.of(courses[0], courses[2])),
                new ParticipantEntity("Pan", false, 612483965, "pan666@gmail.com", List.of(courses[1], courses[2]))
        };
        this.participantRepository.saveAll(Arrays.asList(participants));

        TrainingItemEntity[] trainingItems = {
                new TrainingItemEntity(courses[0], Arrays.asList(lecturers[0], lecturers[1]), "Basic Psychology", "Psychology"),
                new TrainingItemEntity(courses[1], Arrays.asList(lecturers[2], lecturers[3]), "Social Psychology", "Psychology"),
                new TrainingItemEntity(courses[2], Arrays.asList(lecturers[0], lecturers[2]), "Clap training", "Physical education"),
                new TrainingItemEntity(courses[0], Arrays.asList(lecturers[1], lecturers[3]), "Face-to-face interaction", "Practice")
        };
        this.trainingItemRepository.saveAll(Arrays.asList(trainingItems));
    }

    public void deleteAll() {
        this.courseRepository.deleteAll();
        this.trainingItemRepository.deleteAll();
        this.lecturerRepository.deleteAll();
        this.participantRepository.deleteAll();
    }
}
