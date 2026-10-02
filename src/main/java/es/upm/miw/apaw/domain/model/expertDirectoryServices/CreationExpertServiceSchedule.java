package es.upm.miw.apaw.domain.model.expertdirectoryservices;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
    private BigDecimal rateAmount;

    private String currency;

    private String specialCondition;

    private List<@NotNull UUID> legalExpertProfileIds;
}