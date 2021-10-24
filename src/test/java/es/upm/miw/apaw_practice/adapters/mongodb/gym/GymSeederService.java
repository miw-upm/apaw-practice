package es.upm.miw.apaw_practice.adapters.mongodb.gym;

import es.upm.miw.apaw_practice.adapters.mongodb.gym.daos.AthleteRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.gym.daos.CoachRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.gym.daos.GymRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.gym.daos.LessonRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.gym.entities.AthleteEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.gym.entities.CoachEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.gym.entities.GymEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.gym.entities.LessonEntity;
import es.upm.miw.apaw_practice.domain.models.gym.Athlete;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Service
public class GymSeederService {
    @Autowired
    private GymRepository gymRepository ;
    @Autowired
    private AthleteRepository athleteRepository;
    @Autowired
    private LessonRepository lessonRepository;
    @Autowired
    private CoachRepository coachRepository;

    public void seedDatabase(){
        LogManager.getLogger(this.getClass()).warn("------- Gym Initial Load -----------");
        AthleteEntity[] athlete = {
                new AthleteEntity(Athlete.builder().nie("12345678a").name("ada").familyName("perez").build()),
                new AthleteEntity(Athlete.builder().nie("88888888a").name("julia").familyName("Jackson").build()),
                new AthleteEntity(Athlete.builder().nie("55555555a").name("ana").familyName("ramos").build())

        };
        this.athleteRepository.saveAll(Arrays.asList(athlete));
        LessonEntity[] lesson = {
                new LessonEntity("YOGA", LocalDateTime.of(2020, 8, 10, 12, 30), "Yoga", true, List.of(athlete[0], athlete[2])),
                new LessonEntity("BodyComba", LocalDateTime.of(2020, 9, 5, 5, 15), "lesMils", false, List.of(athlete[1], athlete[2])),
                new LessonEntity("RPM", LocalDateTime.of(2020, 12, 2, 1, 45), "lesMils", true, List.of(athlete[1]))
        };
        this.lessonRepository.saveAll(Arrays.asList(lesson));
        CoachEntity[] coach = {
                new CoachEntity("2356892A", "Terry", "Ryan", 11112, lesson[1]),
                new CoachEntity("2458698A", "John", "Chris", 123456789, lesson[2]),
                new CoachEntity("2376698A", "Arnold", "Coleman", 123456789, lesson[2])
        };
        this.coachRepository.saveAll(Arrays.asList(coach));
        GymEntity[] gym = {
                new GymEntity("calle Gran via 82", "Basic Fit", "12356688", List.of(coach[1], coach[2])),
                new GymEntity("calle toledo 32", "Basic Fi", "12356856", List.of(coach[0]))

        };
        this.gymRepository.saveAll(Arrays.asList(gym));


    }
    public void deleteAll(){
        this.athleteRepository.deleteAll();
        this.coachRepository.deleteAll();
        this.gymRepository.deleteAll();
        this.lessonRepository.deleteAll();
    }

}
