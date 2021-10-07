package es.upm.miw.apaw_practice.adapters.mongodb.gym.daos;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.adapters.mongodb.gym.entities.AthleteEntity;

import es.upm.miw.apaw_practice.adapters.mongodb.gym.entities.CoachEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.gym.entities.LessonEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.gym.entities.daos.CoachRepository;
import es.upm.miw.apaw_practice.domain.models.gym.Athlete;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


@TestConfig
public class CoachRepositoryIT {

    @Autowired
    private CoachRepository coachRepository;
    private LessonEntity[] lesson;
    private AthleteEntity[] athlete;


    @BeforeEach
    void initializeTestData() {
        athlete = new AthleteEntity[]{
                new AthleteEntity(new Athlete("88888888a", "julia", "Jackson")),
                new AthleteEntity(new Athlete("55555555a", "ana", "ramos"))
        };
        lesson = new LessonEntity[]{
                new LessonEntity("BodyComba", LocalDateTime.of(2020, 9, 5, 5, 15), "lesMils", false, List.of(athlete[0], athlete[1])),


        };
    }

    @Test
    void testFindByNie() {

        assertTrue(this.coachRepository.findByDni("2356892A").isPresent());
        CoachEntity coach = this.coachRepository.findByDni("2356892A").get();
        assertEquals("Terry", coach.getFirstName());
        assertEquals("Ryan", coach.getLastName());
        assertEquals(11112, coach.getPhone());
        assertEquals(lesson[0], coach.getLesson());


    }

}
