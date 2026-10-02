package es.upm.miw.apaw.domain.model.expertdirectoryservices;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ExpertServiceSchedule {

    @EqualsAndHashCode.Include
    private UUID id;

    @NotBlank
    private String tariffCode;

    @NotBlank
    private String description;

    @NotNull
    private BigDecimal rateAmount;

    private String currency;

    private String specialCondition;

    private LocalDate creationDate;

    private List<LegalExpertProfile> legalExpertProfiles;

    public void doDefault() {
        if (this.id == null) {
            this.id = UUID.randomUUID();
        }
        if (this.creationDate == null) {
            this.creationDate = LocalDate.now();
        }
        if (this.currency == null) {
            this.currency = "EUR";
        }
    }
}