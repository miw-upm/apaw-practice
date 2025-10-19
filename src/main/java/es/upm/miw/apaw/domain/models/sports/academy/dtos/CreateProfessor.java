package es.upm.miw.apaw.domain.models.sports.academy.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateProfessor {
    @NotNull
    public UUID userId;
    @NotNull
    @NotBlank
    public String specialization;
    @NotNull
    @NotBlank
    public String licenseNumber;
}
