package es.upm.miw.apaw.domain.models.fighters;

import es.upm.miw.apaw.domain.models.UserDto;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Rating {
    private UUID id;
    @NotNull
    @Min(0)
    @Max(5)
    private Integer score;
    private String comment;
    private LocalDateTime createdAt;
    @NotNull
    private UserDto user;
}
