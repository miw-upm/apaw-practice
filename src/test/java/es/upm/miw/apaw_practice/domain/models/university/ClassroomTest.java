package es.upm.miw.apaw_practice.domain.models.university;

import es.upm.miw.apaw_practice.TestConfig;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

@TestConfig
public class ClassroomTest {

    @Test
    void testBuilder() {
        Classroom classroom = Classroom.builder().school("ETSISI").number(1302).capacity(20).build();
        Assertions.assertEquals("ETSISI", classroom.getSchool());
        Assertions.assertEquals(1302, classroom.getNumber());
        Assertions.assertEquals(20, classroom.getCapacity());
    }
}
