package es.upm.miw.apaw.functionaltests.videoWebsite;

import es.upm.miw.apaw.adapters.mongodb.videoWebsite.daos.VideoWebSiteSeeder;
import es.upm.miw.apaw.domain.models.UserDto;
import es.upm.miw.apaw.domain.models.videoWebsite.enums.AccountType;
import es.upm.miw.apaw.domain.models.videoWebsite.enums.VideoStatus;
import es.upm.miw.apaw.domain.restclients.UserRestClient;
import org.mockito.BDDMockito;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.junit.jupiter.api.Test;
import es.upm.miw.apaw.domain.models.videoWebsite.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static es.upm.miw.apaw.adapters.resources.videoWebsite.CommentResource.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
@ActiveProfiles("test")
public class CommentResourceFT {
    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private VideoWebSiteSeeder videoWebSiteSeeder;

    @MockitoBean
    private UserRestClient userRestClient;


    @Test
    void testDeleteComment() {
        UUID commentId = UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff1000");

        this.webTestClient
                .delete()
                .uri(COMMENTS + "/" + commentId)
                .exchange()
                .expectStatus().isNoContent();
        videoWebSiteSeeder.deleteAll();
        videoWebSiteSeeder.seedDatabase();
    }

    @Test
    void testCreateComment() {
        UserDto user = UserDto.builder().id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff9990")).build();

        Video video = Video.builder()
                .id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff9991"))
                .title("test_title 1")
                .description("test_Description of 1º video")
                .uploadDate(LocalDateTime.now())
                .videoStatus(VideoStatus.PUBLIC)
                .views(500)
                .build();

        WatchList watchList = WatchList.builder()
                .listName("test_listName 1")
                .description("test_list description 1")
                .savedVideos(List.of(video))
                .build();

        WebAccount commenter = WebAccount.builder()
                .id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff9992"))
                .userName("test_Test Account")
                .accountType(AccountType.NORMAL)
                .user(user)
                .watchList(List.of(watchList))
                .build();

        Comment comment = Comment.builder()
                .id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff9993"))
                .content("Created from controller test")
                .commentTime(LocalDateTime.now())
                .video(video)
                .commenter(commenter)
                .build();

        webTestClient.post()
                .uri("/videoWebsite/comments")
                .bodyValue(comment)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Comment.class)
                .value(c -> assertEquals("Created from controller test", c.getContent()));

        videoWebSiteSeeder.deleteAll();
        videoWebSiteSeeder.seedDatabase();
    }

    @Test
    void testFindCommentersMobileByVideoTitle() {
        BDDMockito.given(this.userRestClient.readById(any(UUID.class)))
                .willAnswer(invocation ->
                        UserDto.builder().id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0010"))
                                .mobile("123456789")
                                .firstName("mock").build());


        webTestClient.get()
                .uri("/videoWebsite/comments/video/{title}/mobiles", "title 1")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(String.class)
                .value(mobiles -> {
                    assertEquals(1, mobiles.size());
                    assertEquals("[\"123456789\"]", mobiles.get(0));
                });
    }
}
