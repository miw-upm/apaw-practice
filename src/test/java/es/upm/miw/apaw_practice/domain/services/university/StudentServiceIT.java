package es.upm.miw.apaw_practice.domain.services.university;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.domain.models.university.Student;
import es.upm.miw.apaw_practice.domain.persistence_ports.university.StudentPersistence;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestConfig
public class StudentServiceIT {

    @Autowired
    private StudentService studentService;

    @Autowired
    private StudentPersistence studentPersistence;

    @Test
    void testCreate() {
        Student student = new Student("987654321P", "Margarita María de Alacoque", true);
        this.studentService.create(student);
        Student persistedStudent = this.studentPersistence.readByDni("987654321P");
        assertEquals(student.getDni(), persistedStudent.getDni());
        assertEquals(student.getFullName(), persistedStudent.getFullName());
        assertEquals(student.getInternationalStudent(), persistedStudent.getInternationalStudent());
    }

    @Test
    void testFindDniListByClassroomSchool() {
        List<String> dniCorrectList = Arrays.asList("12345678X", "112233445E");
        List<String> dniServiceList = this.studentService.findDniListByClassroomSchool("ETSIINF");
        assertTrue(dniCorrectList.size() == dniServiceList.size()
                && dniServiceList.containsAll(dniCorrectList));
    }
}
