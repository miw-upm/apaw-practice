package es.upm.miw.apaw.adapters.mongodb.videoWebsite.persistence;

import es.upm.miw.apaw.adapters.mongodb.videoWebsite.daos.VideoWebSiteSeeder;
import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.models.UserDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import es.upm.miw.apaw.adapters.mongodb.videoWebsite.daos.CommentRepository;
import es.upm.miw.apaw.adapters.mongodb.videoWebsite.entities.*;
import es.upm.miw.apaw.domain.models.videoWebsite.enums.*;
import es.upm.miw.apaw.domain.models.videoWebsite.*;

import java.util.List;
import java.util.Optional;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
public class CommentPersistenceMongodbIT {

    @Autowired
    private CommentPersistenceMongodb commentPersistence;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private VideoWebSiteSeeder videoWebSiteSeeder;

    @Test
    void testDeleteById() {
        UUID commentId = UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff1000");
        this.commentPersistence.deleteById(commentId);
        assertThrows(NotFoundException.class, () -> this.commentPersistence.findById(commentId));
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


        this.commentPersistence.create(comment);

        Optional<CommentEntity> optional = this.commentRepository.findById(comment.getId());
        assertTrue(optional.isPresent());
    }
}
