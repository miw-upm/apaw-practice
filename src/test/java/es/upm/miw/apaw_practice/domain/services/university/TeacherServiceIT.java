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
public class TeacherServiceIT {

    @Autowired
    private TeacherService teacherService;

    public static Stream<Arguments> findDistinctTeacherNationalIdFromStudentFirstName() {
        return Stream.of(
                Arguments.of("Emily", List.of()),
                Arguments.of("James", List.of()),
                Arguments.of("Sophia", List.of("CD876543B")),
                Arguments.of("Michael", List.of("CD876543B", "GH345678E")),
                Arguments.of("Olivia", List.of("CD876543B", "GH345678E"))
        );
    }

    @ParameterizedTest
    @MethodSource()
    void findDistinctTeacherNationalIdFromStudentFirstName(String studentFirstName, List<String> expectedNationalIds) {
        List<String> nationalIdsFound = teacherService.findDistinctTeacherNationalIdFromStudentFirstName(studentFirstName).toList();
        assertEquals(nationalIdsFound.stream().distinct().count(), nationalIdsFound.size());
        assertTrue(nationalIdsFound.containsAll(expectedNationalIds));
    }
}
