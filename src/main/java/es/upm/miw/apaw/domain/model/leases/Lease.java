package es.upm.miw.apaw.domain.model.leases;

import es.upm.miw.apaw.domain.model.UserSnapshot;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Lease {

    @EqualsAndHashCode.Include
    private UUID id;

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

    private LocalDateTime createdAt;

    @NotNull
    private LeaseType leaseType;

    private List<Amendment> amendments;

    @NotNull
    private UserSnapshot userSnapshot;

    public void doDefault() {
        this.id = UUID.randomUUID();
        this.createdAt = LocalDateTime.now();
        if (this.active == null) {
            this.active = true;
        }
    }

    public Lease ofSummary() {
        return Lease.builder()
                .id(this.id)
                .leaseNumber(this.leaseNumber)
                .cadastralReference(this.cadastralReference)
                .propertyAddress(this.propertyAddress)
                .startDate(this.startDate)
                .endDate(this.endDate)
                .monthlyRent(this.monthlyRent)
                .deposit(this.deposit)
                .active(this.active)
                .createdAt(this.createdAt)
                .leaseType(this.leaseType)
                .userSnapshot(UserSnapshot.builder()
                        .id(this.userSnapshot.getId())
                        .mobile(this.userSnapshot.getMobile())
                        .firstName(this.userSnapshot.getFirstName())
                        .build())
                .build();
    }
}
