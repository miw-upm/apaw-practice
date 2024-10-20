package es.upm.miw.apaw_practice.adapters.mongodb.course.persistence;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.domain.models.course.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@TestConfig
class TutoringSessionRepositoryIT {

    @Autowired
    private TutoringSessionPersistenceMongodb tutoringSessionPersistenceMongodb;

    private String tittleTutoringSession;

    @BeforeEach
    void setUp() {
        this.tittleTutoringSession = "Punteros en C";
    }

    @Test
    void testTutoringSessionDelete() {
        this.tutoringSessionPersistenceMongodb.delete(this.tittleTutoringSession);
    }

    @Test
    void testPriceSumOfRoleDuration(){
        BigDecimal sum = this.tutoringSessionPersistenceMongodb.priceSumOfRoleDuration(User.TypeUser.STUDENT.name(), LocalTime.of(0, 57));
        assertNotNull(sum);
        System.out.println("Holi" + sum);
    }
}
