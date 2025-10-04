package es.upm.miw.apaw.domain.models.sports.academy;

import es.upm.miw.apaw.domain.models.UserDto;
import es.upm.miw.apaw.domain.models.sports.academy.enums.RelationShip;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LegalGuardian {
    @NotNull
    @NotBlank
    private String secondMobile;
    @NotNull
    private RelationShip relationShip;
    @NotNull
    private UserDto user;
}
