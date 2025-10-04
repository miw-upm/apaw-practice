package es.upm.miw.apaw.domain.models.sports.academy;

import es.upm.miw.apaw.domain.models.sports.academy.enums.Level;
import es.upm.miw.apaw.domain.models.sports.academy.enums.TargetAudience;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SportModality {
    @NotNull
    private UUID sportId;
    @NotNull
    @NotBlank
    private String title;
    @NotNull
    private Level level;
    @NotNull
    private TargetAudience targetAudience;
    @NotNull
    private boolean active;
    @NotNull
    private Professor professor;
    @NotNull
    private List<Athlete> athletes;
}
