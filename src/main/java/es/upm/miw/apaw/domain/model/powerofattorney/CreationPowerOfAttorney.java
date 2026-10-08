package es.upm.miw.apaw.domain.model.powerofattorney;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreationPowerOfAttorney {

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

    private PowerOfAttorneyStatus status;

    private PowerOfAttorneyType type;

    @NotNull
    private UUID principalId;

    @NotNull
    private UUID attorneyId;
}
