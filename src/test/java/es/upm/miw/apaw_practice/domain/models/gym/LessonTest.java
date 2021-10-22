package es.upm.miw.apaw_practice.domain.models.gym;

import es.upm.miw.apaw_practice.TestConfig;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestConfig
public class LessonTest {

    @Test
    void testBuilder() {
        Athlete[] athlete = {
                new Athlete.Builder().nie("12345678a").name("ada").familyName("perez").build()
        };
        Lesson lesson = Lesson.builder().title("Yoga").
                time(LocalDateTime.of(2020, 8, 10, 12, 30)).
                description("les mils").finished(true).athletes(List.of(athlete[0])).build();

        assertEquals("Yoga", lesson.getTitle());
        assertEquals("les mils", lesson.getDescription());
        assertEquals(LocalDateTime.of(2020, 8, 10, 12, 30), lesson.getTime());
        assertEquals(true, lesson.getFinished());
        assertEquals(List.of(athlete[0]), lesson.getAthletes());

    }
}
