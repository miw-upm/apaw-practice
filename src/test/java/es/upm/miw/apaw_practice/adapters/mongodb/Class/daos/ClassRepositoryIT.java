package es.upm.miw.apaw_practice.adapters.mongodb.Class.daos;

import es.upm.miw.apaw_practice.TestConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertTrue;

@TestConfig
class ClassRepositoryIT {
    @Autowired
    private ClassRepository classRepository;

    @Test
    void testCreateAndRead() {
        assertTrue(this.classRepository.findAll().stream()
                .anyMatch(class1 ->
                        "wen" == class1.getId() &&
                                24 == class1.getCredit() &&
                                "espanol" == class1.getName()

                )

        );

    }
}

