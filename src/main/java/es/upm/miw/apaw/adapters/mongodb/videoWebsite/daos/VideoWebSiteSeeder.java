package es.upm.miw.apaw.adapters.mongodb.videoWebsite.daos;

import es.upm.miw.apaw.adapters.mongodb.videoWebsite.entities.*;
import es.upm.miw.apaw.domain.models.videoWebsite.enums.AccountType;
import es.upm.miw.apaw.domain.models.videoWebsite.enums.VideoStatus;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Repository
@Profile({"dev", "test"})
@Log4j2
public class VideoWebSiteSeeder {

    private final VideoRepository videoRepository;
    private final WebAccountRepository webAccountRepository;
    private final CommentRepository commentRepository;

    @Autowired
    public VideoWebSiteSeeder (VideoRepository videoRepository, WebAccountRepository webAccountRepository, CommentRepository commentRepository) {
        this.videoRepository = videoRepository;
        this.webAccountRepository = webAccountRepository;
        this.commentRepository = commentRepository;
    }

    public void seedDatabase(){
        log.warn("------- VideoWebsite Initial Load -----------");
            VideoEntity[] videoEntities = {
                    VideoEntity.builder()
                            .id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0100"))
                            .title("title 1")
                            .description("Description of 1º video")
                            .uploadDate(LocalDateTime.now())
                            .videoStatus(VideoStatus.PUBLIC)
                            .build(),
                    VideoEntity.builder()
                            .id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0200"))
                            .title("title 2")
                            .description("Description of 2º video")
                            .uploadDate(LocalDateTime.now())
                            .videoStatus(VideoStatus.PUBLIC)
                            .build(),
                    VideoEntity.builder()
                            .id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0300"))
                            .title("title 3")
                            .description("Description of 3º video")
                            .uploadDate(LocalDateTime.now())
                            .videoStatus(VideoStatus.PUBLIC)
                            .build(),
                    VideoEntity.builder()
                            .id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0400"))
                            .title("title 4")
                            .description("Description of 4º video")
                            .uploadDate(LocalDateTime.now())
                            .videoStatus(VideoStatus.PRIVATE)
                            .build(),
                    VideoEntity.builder()
                            .id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0500"))
                            .title("title 5")
                            .description("Description of 5º video")
                            .uploadDate(LocalDateTime.now())
                            .videoStatus(VideoStatus.PROTECT)
                            .build()
            };
            this.videoRepository.saveAll(Arrays.asList(videoEntities));

            WatchListEntity[] watchListEntities = {
                    WatchListEntity.builder()
                            .listName("WatchList 1")
                            .description("Description of 1º WatchList")
                            .build(),
                    WatchListEntity.builder()
                            .listName("WatchList 2")
                            .description("Description of 2º WatchList")
                            .build(),
                    WatchListEntity.builder()
                            .listName("WatchList 3")
                            .description("Description of 3º WatchList")
                            .build(),
                    WatchListEntity.builder()
                            .listName("WatchList 4")
                            .description("Description of 4º WatchList")
                            .build(),
                    WatchListEntity.builder()
                            .listName("WatchList 5")
                            .description("Description of 5º WatchList")
                            .build()
            };

            WebAccountEntity[] webAccountEntities = {
                    WebAccountEntity.builder()
                            .id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0010"))
                            .userName("Account 1")
                            .accountType(AccountType.NORMAL)
                            .userId(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0001"))
                            .watchListEntities(List.of(watchListEntities[0],watchListEntities[1]))
                            .build(),
                    WebAccountEntity.builder()
                            .id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0020"))
                            .userName("Account 2")
                            .accountType(AccountType.PLUS)
                            .userId(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0001"))
                            .watchListEntities(List.of(watchListEntities[2]))
                            .build(),
                    WebAccountEntity.builder()
                            .id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0030"))
                            .userName("Account 3")
                            .accountType(AccountType.PRO)
                            .userId(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0002"))
                            .watchListEntities(List.of(watchListEntities[3]))
                            .build(),
                    WebAccountEntity.builder()
                            .id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0040"))
                            .userName("Account 4")
                            .accountType(AccountType.NORMAL)
                            .userId(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0003"))
                            .watchListEntities(List.of(watchListEntities[4]))
                            .build()
            };
            this.webAccountRepository.saveAll(Arrays.asList(webAccountEntities));

            CommentEntity[] commentEntities = {
                    CommentEntity.builder()
                            .id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff1000"))
                            .content("content 1")
                            .commentTime(LocalDateTime.now())
                            .videoEntity(videoEntities[0])
                            .commenterEntity(webAccountEntities[0])
                            .build(),
                    CommentEntity.builder()
                            .id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff2000"))
                            .content("content 2")
                            .commentTime(LocalDateTime.now())
                            .videoEntity(videoEntities[1])
                            .commenterEntity(webAccountEntities[0])
                            .build(),
                    CommentEntity.builder()
                            .id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff3000"))
                            .content("content 3")
                            .commentTime(LocalDateTime.now())
                            .videoEntity(videoEntities[2])
                            .commenterEntity(webAccountEntities[0])
                            .build(),
                    CommentEntity.builder()
                            .id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff4000"))
                            .content("content 4")
                            .commentTime(LocalDateTime.now())
                            .videoEntity(videoEntities[2])
                            .commenterEntity(webAccountEntities[1])
                            .build(),
                    CommentEntity.builder()
                            .id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff5000"))
                            .content("content 5")
                            .commentTime(LocalDateTime.now())
                            .videoEntity(videoEntities[3])
                            .commenterEntity(webAccountEntities[1])
                            .build(),
                    CommentEntity.builder()
                            .id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff6000"))
                            .content("content 6")
                            .commentTime(LocalDateTime.now())
                            .videoEntity(videoEntities[3])
                            .commenterEntity(webAccountEntities[2])
                            .build()
            };
            commentRepository.saveAll(Arrays.asList(commentEntities));
            log.warn("        ------- shop");

    }

    public void deleteAll(){
        this.commentRepository.deleteAll();
        this.webAccountRepository.deleteAll();
        this.videoRepository.deleteAll();
    }

}
