package es.upm.miw.apaw.adapters.mongodb.studentcouncil.daos;


import es.upm.miw.apaw.adapters.mongodb.studentcouncil.entitites.StudentCouncilEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class StudentCouncilRepositoryIT {

    @Autowired
    private StudentCouncilRepository repository;

    @Test
    void testSaveAndFind() {
        StudentCouncilEntity entity = StudentCouncilEntity.builder()
                .id(UUID.randomUUID())
                .council("ETSII")
                .site("Madrid")
                .resources(BigDecimal.valueOf(10000))
                .build();

        repository.save(entity);
        StudentCouncilEntity found = repository.findById(entity.getId()).orElseThrow();

        assertEquals(entity.getResources(), found.getResources());
        assertEquals(entity.getCouncil(), found.getCouncil());
    }
}