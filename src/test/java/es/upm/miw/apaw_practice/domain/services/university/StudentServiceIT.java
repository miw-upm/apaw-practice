package es.upm.miw.apaw_practice.domain.services.university;

import es.upm.miw.apaw_practice.TestConfig;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestConfig
public class StudentServiceIT {

    @Autowired
    private StudentService studentService;

    public static Stream<Arguments> testFindDistinctStudentEmailsByTeacherLastName() {
        return Stream.of(
                Arguments.of("Carter", List.of()),
                Arguments.of("Anderson", List.of()),
                Arguments.of("Matthews", List.of("sophia.davis@example.org", "michael.brown@example.org", "olivia.wilson@example.org")),
                Arguments.of("Thompson", List.of()),
                Arguments.of("Roberts", List.of("james.miller@example.org", "sophia.davis@example.org", "michael.brown@example.org", "olivia.wilson@example.org"))
        );
    }

    @ParameterizedTest
    @MethodSource()
    void testFindDistinctStudentEmailsByTeacherLastName(String teacherLastName, List<String> expectedEmails) {
        List<String> emailsFound = studentService.findDistinctStudentEmailsByTeacherLastName(teacherLastName).toList();
        assertEquals(emailsFound.stream().distinct().count(), emailsFound.size());
        assertTrue(emailsFound.containsAll(expectedEmails));
    }
}
