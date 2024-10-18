package es.upm.miw.apaw_practice.adapters.mongodb.course.persistence;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.domain.models.course.Video;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@TestConfig
public class VideoPersistenceMongodbIT {

    @Autowired
    private VideoPersistenceMongodb videoPersistenceMongodb;

    private Video video;
    private String tittleVideo;

    @BeforeEach
    void setUp() {
        this.video = createInitialVideo();
        this.tittleVideo = "Spring Framework Básico";
    }

    @Test
    void testUpdatedVideoAttributes() {

        Video updatedVideo = this.videoPersistenceMongodb.update(this.tittleVideo, this.video);

        assertNotNull(updatedVideo);
        assertEquals("Spring Basic Advanced", updatedVideo.getName());
        assertEquals(LocalTime.of(1, 0), updatedVideo.getDuration());
        assertEquals(LocalDateTime.of(2024, 01, 01, 10, 0), updatedVideo.getCreationDate());
        assertEquals(null, updatedVideo.getCourse());
    }

    private static Video createInitialVideo() {
        return new Video(
                "Spring Basic Advanced",
                LocalTime.of(1, 0),
                LocalDateTime.of(2024, 01, 01, 10, 0),
                null
        );
    }
}
