package es.upm.miw.apaw.domain.models.sports.academy;

import es.upm.miw.apaw.domain.models.UserDto;
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
public class Professor {
    @NotNull
    @NotBlank
    private String specialization;
    @NotNull
    @NotBlank
    private String licenseNumber;
    @NotNull
    private UserDto user;
}
