package es.upm.miw.apaw.adapters.mongodb.videogame.persistence;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@ActiveProfiles("test")
public class LikeListPersistanceMongodbIT {

    @Autowired
    private LikeListPersistenceMongoDB likeListPersistenceMongoDB;

    @Test
    void testReadSharedById() {
        assertTrue(this.likeListPersistenceMongoDB.readSharedById(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0020")));
    }
}
