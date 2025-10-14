package es.upm.miw.apaw.adapters.mongodb.university.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LessonEntity {
    private LocalDateTime startDate;
    private String classroom;
    private Integer duration;
}
