package es.upm.miw.apaw_practice.domain.models.course;

import es.upm.miw.apaw_practice.TestConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@TestConfig
class CourseTest {

    private CourseComponent courseComposite;

    @BeforeEach
    void setUp() {
        this.courseComposite= new CourseComposite();
        courseComposite.add(new CourseLeaf(createCourse1()));
        courseComposite.add(new CourseLeaf(createCourse2()));
        courseComposite.add(new CourseLeaf(createCourse3()));
    }

    @Test
    void testBuilder() {
        User user = User.build()
                .email("sofia@gmail.com")
                .firstName("Sofia")
                .role(User.TypeUser.STUDENT_TUTOR)
                .build();

        assertEquals("Sofia", user.getFirstName());
        assertEquals("sofia@gmail.com", user.getEmail());
        assertEquals(User.TypeUser.STUDENT_TUTOR, user.getRole());
        assertNotNull(user.toString());
    }

    @Test
    void testGetCoursesPaymentRequired() {

        Map<String, Boolean> result = courseComposite.getCoursesPaymentsRequired()
                .flatMap(map -> map.entrySet().stream())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        assertEquals(3, result.size());
        assertEquals(true, result.get("Java Basics"));
        assertEquals(false, result.get("Advanced Java"));
        assertEquals(true, result.get("Spring Framework"));
    }

    @Test
    void testGetTitles() {
        Stream<String> titles = courseComposite.getTitles();
        assertEquals(Stream.of("Java Basics", "Advanced Java", "Spring Framework").toList(), titles.toList());
    }

    @Test
    void testGetPaymentsRequired() {
        Stream<Boolean> paymentsRequired = courseComposite.getPaymentsRequired();
        assertEquals(Stream.of(true, false, true).toList(), paymentsRequired.toList());
    }

    private Course createCourse1() {
        Course course = new Course("Java Basics", true, LocalDate.of(2023, 1, 10), LocalDate.of(2023, 4, 10));
        course.setTutoringSession(new TutoringSession("Intro to Java", LocalDateTime.of(2023, 1, 15, 10,0), BigDecimal.valueOf(100)));
        course.setTutoringSession(new TutoringSession("Advanced Topics", LocalDateTime.of(2023, 6, 10, 10,0), BigDecimal.valueOf(150)));
        course.setUser(new User("Sofia", "sofia@gmail.com", User.TypeUser.STUDENT));
        course.setUser(new User("Daniel", "daniel@gmail.com", User.TypeUser.STUDENT_TUTOR));
        return course;
    }

    private Course createCourse2() {
        Course course = new Course("Advanced Java", false, LocalDate.of(2023, 5, 1), LocalDate.of(2023, 8, 1));
        course.setTutoringSession(new TutoringSession("Intro to Java", LocalDateTime.of(2023, 1, 15,  10,0), BigDecimal.valueOf(100)));
        course.setUser(new User("Sofia", "sofia@gmail.com", User.TypeUser.STUDENT));
        return course;
    }

    private Course createCourse3() {
        Course course = new Course("Spring Framework", true, LocalDate.of(2023, 3, 15), LocalDate.of(2023, 6, 15));
        course.setTutoringSession(new TutoringSession("Intro to Spring", LocalDateTime.of(2023, 3, 20, 10,0), BigDecimal.valueOf(200)));
        course.setUser(new User("Daniel", "daniel@gmail.com", User.TypeUser.STUDENT_TUTOR));
        return course;
    }
}
