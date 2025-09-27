package es.upm.miw.apaw.domain.models.sports.academy;

import es.upm.miw.apaw.domain.models.UserDto;
import es.upm.miw.apaw.domain.models.sports.academy.enums.Gender;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class Athlete {
    @NotNull
    private Gender gender;
    @NotNull
    @Positive
    private double height;
    @NotNull
    @Positive
    private double weight;
    @NotNull
    private UserDto user;
    @NotNull
    private LegalGuardian legalGuardian;
}
