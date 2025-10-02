package es.upm.miw.apaw.domain.models.fighters;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Coach {
    @NotNull
    private UUID coachNumber;
    @NotBlank
    private String fullName;
    @NotBlank
    private String academy;
    @Min(0)
    private Integer experienceYears;
    @Builder.Default
    private List<Fighter> fighters = new ArrayList<>();
}
