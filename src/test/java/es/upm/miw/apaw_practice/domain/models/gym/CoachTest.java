package es.upm.miw.apaw_practice.domain.models.gym;

import es.upm.miw.apaw_practice.TestConfig;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestConfig
public class CoachTest {

    @Test
    void testBuilder() {
        Lesson lesson = Lesson.builder().title("Yoga").
                time(LocalDateTime.of(2020, 8, 10, 12, 30)).
                description("les mils").finished(true).athletes(new ArrayList<>()).build();

        Coach coach = Coach.builder().dni("12345678a")
                .firstName("karim")
                .lastname("escobar")
                .phone(123456)
                .lessons(lesson)
                .bulid();
        assertEquals("12345678a", coach.getDni());
        assertEquals("karim", coach.getFirstName());
        assertEquals("escobar", coach.getLastName());
        assertEquals(123456, coach.getPhone());
        assertEquals(lesson, coach.getLesson());
    }
}
