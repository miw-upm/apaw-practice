package es.upm.miw.apaw.domain.services.videoWebsite;

import es.upm.miw.apaw.adapters.mongodb.videoWebsite.daos.VideoWebSiteSeeder;
import es.upm.miw.apaw.adapters.mongodb.videoWebsite.entities.VideoEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import es.upm.miw.apaw.domain.models.videoWebsite.*;
import es.upm.miw.apaw.domain.models.videoWebsite.enums.*;

import java.time.LocalDateTime;
import java.util.UUID;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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

    @Test
    void testUpdateVideoStatus() {
        UUID videoId = UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0100");

        Stream<VideoStatusUpdating> updates = Stream.of(
                VideoStatusUpdating.builder()
                        .id(videoId)
                        .status(VideoStatus.PROTECT)
                        .build()
        );

        this.videoService.updateVideoStatus(updates);

        Video updated = videoService.findById(videoId);
        assertEquals(VideoStatus.PROTECT, updated.getVideoStatus());

        videoWebSiteSeeder.deleteAll();
        videoWebSiteSeeder.seedDatabase();

    }

    @Test
    void testFindById (){
        Video video = this.videoService.findById(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0100"));
        assertNotNull(video);
        assertThat(video.getTitle()).isEqualTo("title 1");
        assertThat(video.getDescription()).isEqualTo("Description of 1º video");
        assertThat(video.getVideoStatus()).isEqualTo(VideoStatus.PUBLIC);
    }

    @Test
    void testSave (){
        UUID videoId = UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff9998");

        Video newVideo = Video.builder()
                .id(videoId)
                .title("save video title")
                .description("save video description")
                .videoStatus(VideoStatus.PROTECT)
                .build();
        this.videoService.save(newVideo);
        Video video = this.videoService.findById(videoId);
        assertNotNull(video);
        assertThat(video.getTitle()).isEqualTo("save video title");
        assertThat(video.getDescription()).isEqualTo("save video description");
        assertThat(video.getVideoStatus()).isEqualTo(VideoStatus.PROTECT);

        videoWebSiteSeeder.deleteAll();
        videoWebSiteSeeder.seedDatabase();
    }

}
