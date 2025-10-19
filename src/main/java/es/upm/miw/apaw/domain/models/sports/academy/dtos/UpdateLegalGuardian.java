package es.upm.miw.apaw.domain.models.sports.academy.dtos;

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
public class UpdateLegalGuardian {
    @NotNull
    @NotBlank
    public String secondMobile;
    @NotNull
    public RelationShip relationShip;
}
