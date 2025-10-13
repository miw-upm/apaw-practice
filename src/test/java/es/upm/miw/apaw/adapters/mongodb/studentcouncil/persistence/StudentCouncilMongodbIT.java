package es.upm.miw.apaw.adapters.mongodb.studentcouncil.persistence;

import es.upm.miw.apaw.domain.models.studentcouncil.StudentCouncil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class StudentCouncilMongodbIT {

    @Autowired
    private StudentCouncilPersistenceMongodb persistence;

    @Test
    void testUpdateResourcesPersistence() {

        UUID existingId = UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0000");


        StudentCouncil entity = persistence.readById(existingId)
                .orElseThrow(() -> new RuntimeException("StudentCouncil no encontrado en la DB"));

        entity.setResources(BigDecimal.valueOf(7000));
        persistence.update(entity);
        StudentCouncil updated = persistence.readById(existingId)
                .orElseThrow(() -> new RuntimeException("StudentCouncil no encontrado tras update"));

        assertEquals(BigDecimal.valueOf(7000), updated.getResources());
    }
}