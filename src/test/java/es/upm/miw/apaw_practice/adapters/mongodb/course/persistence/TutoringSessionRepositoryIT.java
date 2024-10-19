package es.upm.miw.apaw_practice.adapters.mongodb.course.persistence;

import es.upm.miw.apaw_practice.TestConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

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
}
