package es.upm.miw.apaw.domain.services.videoWebsite;

import es.upm.miw.apaw.domain.persistenceports.videoWebsite.VideoPersistence;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@ActiveProfiles("test")
public class VideoServiceIT {

    @Autowired
    private VideoService videoService;

    @Autowired
    private VideoPersistence videoPersistence;

    @Test
    void testfindByTitle(){
        assertThat(this.videoService.findByTitle("title 1")).hasSize(1);
    }
}
