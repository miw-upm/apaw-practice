package es.upm.miw.apaw.adapters.mongodb.videoWebsite.daos;

import es.upm.miw.apaw.adapters.mongodb.videoWebsite.entities.VideoEntity;
import es.upm.miw.apaw.domain.models.videoWebsite.Video;
import es.upm.miw.apaw.domain.models.videoWebsite.enums.VideoStatus;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@ActiveProfiles("test")
public class VideoRepositoryIT {
    @Autowired
    private VideoRepository videoRepository;

    @Test
    void testFindByTitle(){
        assertFalse(this.videoRepository.findByTitle("title 1").isEmpty());
        VideoEntity video = this.videoRepository.findByTitle("title 1").getFirst();
        assertThat(video.getDescription()).isEqualTo("Description of 1º video");
        assertThat(video.getVideoStatus()).isEqualTo(VideoStatus.PUBLIC);
        assertThat(video.getId()).isEqualTo(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0100"));
        assertThat(video.getViews()).isEqualTo(10000);
    }
}
