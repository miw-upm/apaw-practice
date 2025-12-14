package es.upm.miw.apaw.domain.models.clinic;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Diagnosis {

    @NotBlank
    private String code;

    @NotNull
    private LocalDateTime diagnosisDate;

    private Integer severityLevel;

    private String notes;

    private List<Treatment> treatments;
}