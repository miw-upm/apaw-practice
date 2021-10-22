package es.upm.miw.apaw_practice.adapters.mongodb.Class.persistence;

import es.upm.miw.apaw_practice.adapters.mongodb.Class.daos.ClassRepository;
import es.upm.miw.apaw_practice.domain.models.Class.Class;
import es.upm.miw.apaw_practice.TestConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

@TestConfig
public class ClassPersistenceMongodbIT {
    @Autowired
    private ClassPersistenceMongodb classPersistenceMongodb;

    @Autowired
    private ClassRepository classRepository;

    @Test
    void testCreateAndRead(){
        Class myClass = new Class("android",6, LocalDate.of(2021,10,11));
        this.classPersistenceMongodb.create(myClass);
        assertTrue(this.classRepository.findAll().stream()
                .anyMatch(theClass ->
                        "android".equals(theClass.getName()) &&
                                6 == theClass.getCredit() &&
                                LocalDate.of(2021,10,11).equals(theClass.getStartTime())
                        ));
    }
}
