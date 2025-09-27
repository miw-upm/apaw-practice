package es.upm.miw.apaw.domain.models.sports.academy;

import es.upm.miw.apaw.domain.models.sports.academy.enums.Level;
import es.upm.miw.apaw.domain.models.sports.academy.enums.TargetAudience;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public class SportModality {
    @NotNull
    private UUID id;
    @NotNull
    @NotBlank
    private String title;
    @NotNull
    private Level level;
    @NotNull
    private TargetAudience targetAudience;
    @NotNull
    private boolean isActive;
    @NotNull
    private Place place;
    @NotNull
    private Time time;
    @NotNull
    private Professor professor;
    @NotNull
    private Athlete[] athletes;
}
