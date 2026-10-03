package es.upm.miw.apaw.domain.model.copyright;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClaimCreation {

    @NotBlank
    private String number;

    @NotNull
    private BigDecimal requestedCompensation;

    private Boolean urgent;

    private String resolutionNotes;

    @NotNull
    private UUID creativeWorkId;

    @NotNull
    private UUID defendantId;
}
