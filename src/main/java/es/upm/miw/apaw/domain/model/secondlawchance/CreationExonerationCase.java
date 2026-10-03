package es.upm.miw.apaw.domain.model.secondlawchance;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreationExonerationCase {

    @NotBlank
    private String caseNumber;

    private LocalDate resolutionDate;

    @Pattern(regexp = ".*\\S.*")
    private String lawyer;

    @NotEmpty
    private List<@NotNull UUID> debtIds;

    @NotNull
    private UUID userId;
}
