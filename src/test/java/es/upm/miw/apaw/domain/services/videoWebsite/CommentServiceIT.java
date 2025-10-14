package es.upm.miw.apaw.domain.services.videoWebsite;

import es.upm.miw.apaw.adapters.mongodb.videoWebsite.daos.CommentRepository;
import es.upm.miw.apaw.adapters.mongodb.videoWebsite.daos.VideoWebSiteSeeder;
import es.upm.miw.apaw.domain.models.UserDto;
import es.upm.miw.apaw.domain.models.videoWebsite.enums.AccountType;
import es.upm.miw.apaw.domain.models.videoWebsite.enums.VideoStatus;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import static org.junit.jupiter.api.Assertions.*;
import es.upm.miw.apaw.domain.models.videoWebsite.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@SpringBootTest
@ActiveProfiles("test")
public class CommentServiceIT {
    @Autowired
    private CommentService commentService;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private VideoWebSiteSeeder videoWebSiteSeeder;

    @Test
    void testDeleteById() {
        UUID commentId = UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff1000");
        assertTrue(this.commentRepository.findById(commentId).isPresent());
        this.commentService.deleteById(commentId);
        assertFalse(this.commentRepository.findById(commentId).isPresent());

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
                .content("test_This is a test comment")
                .commentTime(LocalDateTime.now())
                .video(video)
                .commenter(commenter)
                .build();

        Comment saved = this.commentService.create(comment);
        assertNotNull(saved.getId());
        assertEquals("test_This is a test comment", saved.getContent());
    }
}
