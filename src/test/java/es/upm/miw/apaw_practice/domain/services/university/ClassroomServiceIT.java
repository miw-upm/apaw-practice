package es.upm.miw.apaw_practice.domain.services.university;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.persistence_ports.university.ClassroomPersistence;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@TestConfig
public class ClassroomServiceIT {

    @Autowired
    private ClassroomService classroomService;

    @Autowired
    private ClassroomPersistence classroomPersistence;

    @Test
    void testDelete() {
        this.classroomService.delete("ETSISI", 4302);
        assertThrows(NotFoundException.class, () ->
                this.classroomPersistence.readBySchoolAndNumber("ETSISI", 4302));
    }

    @Test
    void testFindCapacitySumByStudentDni() {
        Integer CapacitySum = this.classroomService.findCapacitySumByStudentDni("112233445E");
        assertEquals(70, CapacitySum);
    }
}
