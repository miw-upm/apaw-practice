package es.upm.miw.apaw.domain.model.copyright;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreativeWorkCreation {

    private String registrationCode;

    @NotBlank
    private String title;

    @NotNull
    private BigDecimal estimatedValuation;

    @NotBlank
    private String authorPenName;

    private FormatType formatType;

    @NotBlank
    private UUID authorId;
}
