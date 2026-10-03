package es.upm.miw.apaw.domain.model.powerofattorney;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import java.time.LocalDate;
import java.util.UUID;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class PowerOfAttorney {

    @EqualsAndHashCode.Include
    private UUID id;

    @NotBlank
    private String protocolNumber;

    @NotNull
    private LocalDate grantDate;

    private LocalDate expirationDate;

    @NotBlank
    private String scope;

    private String limitations;

    @NotBlank
    private String notaryName;

    @NotBlank
    private String notaryOffice;

    private String notes;

    @NotNull
    private PowerOfAttorneyParty principal;

    @NotNull
    private PowerOfAttorneyParty attorney;

    private PowerOfAttorneyType type;

    private PowerOfAttorneyStatus status;

    public void doDefault() {
        this.id = UUID.randomUUID();

        if (this.type == null) {
            this.type = PowerOfAttorneyType.GENERAL;
        }

        if (this.status == null) {
            this.status = PowerOfAttorneyStatus.ACTIVE;
        }
    }
}
