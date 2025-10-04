package es.upm.miw.apaw.domain.models.sports.academy;

import es.upm.miw.apaw.domain.models.UserDto;
import es.upm.miw.apaw.domain.models.sports.academy.enums.Gender;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
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
    private LocalDate birthDate;
    @NotNull
    private UserDto user;
    @NotNull
    private List<LegalGuardian> legalGuardians;
    @NotNull
    private List<SportModality> sportModalities;
}
