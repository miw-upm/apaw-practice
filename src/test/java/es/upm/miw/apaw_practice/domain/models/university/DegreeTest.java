package es.upm.miw.apaw_practice.domain.models.university;

import es.upm.miw.apaw_practice.TestConfig;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestConfig
public class DegreeTest {

    @Test
    void testBuilder() {
        Degree instance = Degree.builder()
                .code(1234)
                .capacity(56)
                .knowledgeArea("Unit Testing")
                .description("An example of use of builder pattern")
                .build();
        assertEquals(1234, instance.getCode());
        assertEquals(56, instance.getCapacity());
        assertEquals("Unit Testing", instance.getKnowledgeArea());
        assertEquals("An example of use of builder pattern", instance.getDescription());
    }
}
