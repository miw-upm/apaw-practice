package es.upm.miw.apaw.adapters.mongodb.videoWebsite.entities;

import es.upm.miw.apaw.domain.models.videoWebsite.Video;
import es.upm.miw.apaw.domain.models.videoWebsite.enums.VideoStatus;
import lombok.*;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Document
public class VideoEntity {
    @EqualsAndHashCode.Include
    @Id
    private UUID id;
    private String title;
    private String description;
    private LocalDateTime uploadDate;
    private VideoStatus videoStatus;
    private Integer views;

    public VideoEntity(Video video) {
        BeanUtils.copyProperties(video, this);
    }

    public Video toVideo() {
        return new Video(this.id, this.title, this.description, this.uploadDate, this.videoStatus, this.views);
    }
}
