package es.upm.miw.apaw.domain.model.leases;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreationLease {

    @NotBlank
    private String leaseNumber;

    private String cadastralReference;

    @NotBlank
    private String propertyAddress;

    @NotNull
    private LocalDate startDate;

    private LocalDate endDate;

    @NotNull
    private BigDecimal monthlyRent;

    private BigDecimal deposit;

    private Boolean active;

    @NotNull
    private LeaseType leaseType;

    private List<@NotNull UUID> amendmentIds;

    @NotNull
    private UUID userId;

    public boolean hasValidPeriod() {
        return this.endDate == null || !this.endDate.isBefore(this.startDate);
    }
}
