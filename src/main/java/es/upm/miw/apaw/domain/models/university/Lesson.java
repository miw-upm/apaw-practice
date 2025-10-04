package es.upm.miw.apaw.domain.models.university;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Lesson {
    @NotNull
    private LocalDateTime startDate;
    private String classroom;
    private Integer duration;
}
