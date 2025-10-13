package es.upm.miw.apaw.adapters.mongodb.videoWebsite.persistence;


import es.upm.miw.apaw.domain.models.videoWebsite.Video;
import es.upm.miw.apaw.domain.models.videoWebsite.enums.VideoStatus;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.UUID;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@ActiveProfiles("test")
public class VideoPersistanceMongodbIT {

    @Autowired
    private VideoPersistenceMongodb videoPersistence;

    @Test
    void testFindByTitle(){
        Stream<Video> videos = this.videoPersistence.findByTitle("title 1");
        Video video = videos.findFirst().orElse(null);
        assertNotNull(video);
        assertThat(video.getTitle()).isEqualTo("title 1");
        assertThat(video.getDescription()).isEqualTo("Description of 1º video");
        assertThat(video.getVideoStatus()).isEqualTo(VideoStatus.PUBLIC);
        assertThat(video.getId()).isEqualTo(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0100"));
    }


}
