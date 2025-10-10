package es.upm.miw.apaw.domain.services.videogame;

import es.upm.miw.apaw.domain.persistenceports.videogame.LikeListPersistence;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@ActiveProfiles("test")
public class LikeListServiceIT {

    @Autowired
    private LikeListService likeListService;

    @Autowired
    private LikeListPersistence likeListPersistence;

    @Test
    void testReadSharedById() {
        assertTrue(this.likeListService.readSharedById(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0020")));
    }
}
