package es.upm.miw.apaw.adapters.mongodb.videoWebsite.entities;

import es.upm.miw.apaw.domain.models.videoWebsite.Video;
import es.upm.miw.apaw.domain.models.videoWebsite.WebAccount;
import es.upm.miw.apaw.domain.models.videoWebsite.Comment;
import lombok.*;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Document
public class CommentEntity {
    @EqualsAndHashCode.Include
    @Id
    private UUID id;
    private String content;
    private LocalDateTime commentTime;
    @DBRef
    private WebAccountEntity commenterEntity;
    @DBRef
    private VideoEntity videoEntity;

    public CommentEntity(Comment comment) {
        BeanUtils.copyProperties(comment, this, "commenterEntity", "videoEntity");
        this.commenterEntity = new WebAccountEntity(comment.getCommenter());
        this.videoEntity = new VideoEntity(comment.getVideo());
    }

    public Comment toComment(){
        Comment comment = new Comment();
        BeanUtils.copyProperties(this, comment, "commenter", "video");
        comment.setCommenter(WebAccount.builder().id(commenterEntity.getId()).build());
        comment.setVideo(Video.builder().id(videoEntity.getId()).build());
        return comment;
    }

}
