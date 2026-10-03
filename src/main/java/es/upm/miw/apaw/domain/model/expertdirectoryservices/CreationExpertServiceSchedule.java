package es.upm.miw.apaw.domain.model.expertdirectoryservices;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreationExpertServiceSchedule {

    @NotBlank
    private String tariffCode;

    @NotBlank
    private String description;

    @NotNull
    @Positive
    private BigDecimal rateAmount;

    @Pattern(regexp = "[A-Z]{3}")
    private String currency;

    private String specialCondition;

    private List<@NotNull UUID> legalExpertProfileIds;
}
