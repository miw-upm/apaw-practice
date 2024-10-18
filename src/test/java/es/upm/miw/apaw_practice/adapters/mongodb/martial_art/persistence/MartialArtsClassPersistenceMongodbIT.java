package es.upm.miw.apaw_practice.adapters.mongodb.martial_art.persistence;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.adapters.mongodb.martial_art.persistence.MartialArtsClassPersistenceMongodb;
import es.upm.miw.apaw_practice.adapters.mongodb.martial_art.persistence.TechniquePersistenceMongodb;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.models.martial_art.Technique;
import es.upm.miw.apaw_practice.domain.models.martial_art.MartialArtsClass;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@TestConfig

public class MartialArtsClassPersistenceMongodbIT {
    @Autowired
    private MartialArtsClassPersistenceMongodb martialArtsClassPersistenceMongodb;
    private TechniquePersistenceMongodb techniquePersistenceMongodb;
    @Test
    void testReadNotFound() {
        assertThrows(NotFoundException.class, () -> this.martialArtsClassPersistenceMongodb.read("0"));
    }

    @Test
    void testNameNotExists() {
        assertFalse(this.martialArtsClassPersistenceMongodb.existsByName("0"));
    }

    @Test
    void testNameExists() {
        assertTrue(this.martialArtsClassPersistenceMongodb.existsByName("Taekwondo"));
    }
/*
    @Test
    void testCreateAndRead() {

        MartialArtsClass martialArtsClass;
        martialArtsClass = new MartialArtsClass(
                "Judo",
                LocalDate.of(2024, 10, 7),
                "judoki",
                new ArrayList<>()
                );
        this.martialArtsClassPersistenceMongodb.create(martialArtsClass);
        MartialArtsClass martialArtsClass1 = this.martialArtsClassPersistenceMongodb.read("Z1551645C");
        assertEquals("Z1551645C", martialArtsClass1.getName());;
        assertEquals("Bastian yellow", martialArtsClass1.getClass());
        assertEquals("forestal", martialArtsClass1.getAcademy());
    }*/
}
