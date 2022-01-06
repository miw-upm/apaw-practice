package es.upm.miw.apaw_practice.adapters.mongodb.training;

import es.upm.miw.apaw_practice.adapters.mongodb.training.daos.CourseRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.training.daos.LecturerRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.training.daos.ParticipantRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.training.daos.TrainingItemRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.training.entities.CourseEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.training.entities.LecturerEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.training.entities.ParticipantEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.training.entities.TrainingItemEntity;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Arrays;

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

        ParticipantEntity[] participants = {
                new ParticipantEntity("Luis", true, "4823468X", "874326783", "luis3215@gmail.com"),
                new ParticipantEntity("Mario", true, "4956789A", "523416211", "mario12@gmail.com"),
                new ParticipantEntity("Juan", false, "9866769D", "643167221", "juan99@gmail.com"),
                new ParticipantEntity("Sebastian", false, "4879712Y", "655216879", "sebastian808@gmail.com"),
                new ParticipantEntity("Pan", false, "1657689V", "612483965", "pan666@gmail.com")
        };
        this.participantRepository.saveAll(Arrays.asList(participants));

        LecturerEntity[] lecturers = {
                new LecturerEntity("Jose",  "3386275R", "542765321"),
                new LecturerEntity("Pablo", "1468048B", "963422462"),
                new LecturerEntity("Teresa", "7732893D", "245473227"),
                new LecturerEntity("Fresa", "7114831L", "874735577")
        };
        this.lecturerRepository.saveAll(Arrays.asList(lecturers));

        TrainingItemEntity[] trainingItems = {
                new TrainingItemEntity(lecturers[0], "Basic Psychology", "Psychology"),
                new TrainingItemEntity(lecturers[1], "Social Psychology", "Psychology"),
                new TrainingItemEntity(lecturers[2], "Clap training", "Physical education"),
                new TrainingItemEntity(lecturers[3], "Face-to-face interaction", "Practice")
        };
        this.trainingItemRepository.saveAll(Arrays.asList(trainingItems));

        CourseEntity[] courses = {
                new CourseEntity(Arrays.asList(participants), Arrays.asList(trainingItems), 1, "A", LocalDate.of(2021, 12, 3), LocalDate.of(2021, 12, 4)),
                new CourseEntity(Arrays.asList(participants), Arrays.asList(trainingItems), 2, "C", LocalDate.of(2022, 1, 7), LocalDate.of(2022, 1, 8)),
                new CourseEntity(Arrays.asList(participants), Arrays.asList(trainingItems), 3, "A", LocalDate.of(2022, 6, 3), LocalDate.of(2022, 6, 5))
        };
        this.courseRepository.saveAll(Arrays.asList(courses));
    }

    public void deleteAll() {
        this.courseRepository.deleteAll();
        this.trainingItemRepository.deleteAll();
        this.lecturerRepository.deleteAll();
        this.participantRepository.deleteAll();
    }
}
