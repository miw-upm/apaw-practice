package es.upm.miw.apaw.adapters.mongodb.university.entities;

import es.upm.miw.apaw.domain.models.university.Lesson;
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

    public Lesson toLesson() {
        return Lesson.builder()
                .startDate(this.startDate)
                .classroom(this.classroom)
                .duration(this.duration)
                .build();
    }
}
