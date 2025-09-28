package es.upm.miw.apaw.domain.models.university;

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
public class Teacher {
    @NotNull
    @NotBlank
    private String dni;
    private String specialization;
    private Boolean isTenured;
}
