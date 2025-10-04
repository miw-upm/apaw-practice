package es.upm.miw.apaw.adapters.mongodb.university.entities;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LessonEntity {
    private LocalDateTime startDate;
    private String classroom;
    private Integer duration;
}
