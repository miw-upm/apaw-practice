package es.upm.miw.apaw_practice.adapters.mongodb.Class.daos;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.adapters.mongodb.Class.entities.ClassEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@TestConfig
class ClassRepositoryIT {
    @Autowired
    private ClassRepository classRepository;

    @Test
    void testCreateAndRead() {
        List<ClassEntity> list = this.classRepository.findAll();
        assertTrue(list.stream()
            .anyMatch(myClass ->
                60 == myClass.getCredit() &&
                "class1".equals(myClass.getName())
            ));
    }
}

