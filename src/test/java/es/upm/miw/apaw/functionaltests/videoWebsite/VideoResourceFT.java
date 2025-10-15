package es.upm.miw.apaw.functionaltests.videoWebsite;

import es.upm.miw.apaw.adapters.mongodb.videoWebsite.daos.VideoWebSiteSeeder;
import es.upm.miw.apaw.domain.models.videoWebsite.Video;
import es.upm.miw.apaw.domain.models.videoWebsite.VideoStatusUpdating;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;
import es.upm.miw.apaw.domain.models.videoWebsite.enums.*;


import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
@ActiveProfiles("test")
public class VideoResourceFT {

    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private VideoWebSiteSeeder videoWebSiteSeeder;

    @Test
    void testFindByTitle() {
        webTestClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/videoWebsite/videos")
                        .queryParam("title", "title 1")
                        .build())
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Video.class)
                .value(videos -> {
                    assertThat(videos).isNotEmpty();
                    assertThat(videos.getFirst().getTitle()).isEqualTo("title 1");
                });
    }

    @Test
    void testUpdateVideo() {
        UUID videoId = UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0100");

        Video updatedVideo = Video.builder()
                .title("Updated Title")
                .description("Updated description")
                .videoStatus(VideoStatus.PRIVATE)
                .build();

        webTestClient.put()
                .uri("/videoWebsite/videos/" + videoId)
                .bodyValue(updatedVideo)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Video.class)
                .value(v -> {
                    assertEquals("Updated Title", v.getTitle());
                    assertEquals(VideoStatus.PRIVATE, v.getVideoStatus());
                });
        videoWebSiteSeeder.deleteAll();
        videoWebSiteSeeder.seedDatabase();
    }


    @Test
    void testPatchVideoStatus() {

        UUID videoId = UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0100");

        Stream<VideoStatusUpdating> updates = Stream.of(
                VideoStatusUpdating.builder()
                        .id(videoId)
                        .status(VideoStatus.PROTECT)
                        .build()
        );

        webTestClient.patch()
                .uri("/videoWebsite/videos")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(updates)
                .exchange()
                .expectStatus().isOk();
        videoWebSiteSeeder.deleteAll();
        videoWebSiteSeeder.seedDatabase();

    }

    @Test
    void testPatchVideoStatusNotFound() {

        UUID videoId = UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0110");

        Stream<VideoStatusUpdating> updates = Stream.of(
                VideoStatusUpdating.builder()
                        .id(videoId)
                        .status(VideoStatus.PROTECT)
                        .build()
        );

        webTestClient.patch()
                .uri("/videoWebsite/videos")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(updates)
                .exchange()
                .expectStatus().isNotFound();
        videoWebSiteSeeder.deleteAll();
        videoWebSiteSeeder.seedDatabase();

    }

}
