package es.upm.miw.apaw.domain.services.videogame;

import es.upm.miw.apaw.domain.models.UserDto;
import es.upm.miw.apaw.domain.restclients.UserRestClient;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
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

    @Test
    void testReadSharedById() {
        assertTrue(this.likeListService.readSharedById(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0020")));
    }
}
