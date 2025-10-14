package es.upm.miw.apaw.domain.services.videoWebsite;

import es.upm.miw.apaw.adapters.mongodb.videoWebsite.daos.VideoWebSiteSeeder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import es.upm.miw.apaw.domain.models.videoWebsite.*;
import es.upm.miw.apaw.domain.models.videoWebsite.enums.*;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ActiveProfiles("test")
public class VideoServiceIT {

    @Autowired
    private VideoService videoService;

    @Autowired
    private VideoWebSiteSeeder videoWebSiteSeeder;

    @Test
    void testfindByTitle(){
        assertThat(this.videoService.findByTitle("title 1")).hasSize(1);
    }

    @Test
    void testUpdateVideo() {
        Video video = Video.builder()
                .id(UUID.randomUUID())
                .title("before update")
                .description("before desc")
                .uploadDate(LocalDateTime.now())
                .videoStatus(VideoStatus.PUBLIC)
                .build();

        Video created = this.videoService.save(video);

        Video newData = Video.builder()
                .title("after update")
                .description("after desc")
                .videoStatus(VideoStatus.PRIVATE)
                .build();

        Video updated = this.videoService.update(created.getId(), newData);

        assertEquals("after update", updated.getTitle());
        assertEquals("after desc", updated.getDescription());
        assertEquals(VideoStatus.PRIVATE, updated.getVideoStatus());

        videoWebSiteSeeder.deleteAll();
        videoWebSiteSeeder.seedDatabase();

    }

}
