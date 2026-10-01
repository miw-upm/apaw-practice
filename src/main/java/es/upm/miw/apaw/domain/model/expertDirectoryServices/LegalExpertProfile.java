package es.upm.miw.apaw.domain.model.expertdirectoryservices;

import es.upm.miw.apaw.domain.model.UserSnapshot;
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
public class LegalExpertProfile {

    @EqualsAndHashCode.Include
    private UUID id;

    @NotBlank
    private String taxIdCode;

    private String professionalLicense;

    @NotBlank
    private String specialtyArea;

    @NotNull
    private Integer yearsOfExperience;

    private Boolean requiresPrepayment;

    private LocalDate partnershipDate;

    private UserSnapshot userSnapshot;

    public void doDefault() {
        if (this.id == null) {
            this.id = UUID.randomUUID();
        }
        if (this.partnershipDate == null) {
            this.partnershipDate = LocalDate.now();
        }
        if (this.requiresPrepayment == null) {
            this.requiresPrepayment = false;
        }
    }

    public LegalExpertProfile ofSummary() {
        return LegalExpertProfile.builder()
                .id(this.id)
                .taxIdCode(this.taxIdCode)
                .specialtyArea(this.specialtyArea)
                .userSnapshot(UserSnapshot.builder()
                        .id(this.userSnapshot.getId())
                        .mobile(this.userSnapshot.getMobile())
                        .firstName(this.userSnapshot.getFirstName())
                        .build())
                .build();
    }
}