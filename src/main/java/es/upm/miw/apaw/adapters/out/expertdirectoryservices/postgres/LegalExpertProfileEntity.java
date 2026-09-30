package es.upm.miw.apaw.adapters.out.expertdirectoryservices.postgres;

import es.upm.miw.apaw.domain.model.UserSnapshot;
import es.upm.miw.apaw.domain.model.expertdirectoryservices.LegalExpertProfile;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.beans.BeanUtils;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class LegalExpertProfileEntity {

    @Id
    @EqualsAndHashCode.Include
    private UUID id;

    @Column(nullable = false, unique = true)
    private String taxIdCode;

    @Column(unique = true)
    private String professionalLicense;

    @Column(nullable = false)
    private String specialtyArea;

    @Column(nullable = false)
    private Integer yearsOfExperience;

    @Column(nullable = false)
    private Boolean requiresPrepayment;

    @Column(nullable = false)
    private LocalDate partnershipDate;

    @Column(nullable = false)
    private UUID userId;

    public LegalExpertProfileEntity(LegalExpertProfile legalExpertProfile) {
        BeanUtils.copyProperties(legalExpertProfile, this, "userSnapshot");
        if (legalExpertProfile.getUserSnapshot() != null) {
            this.userId = legalExpertProfile.getUserSnapshot().getId();
        }
    }

    public LegalExpertProfile toDomain() {
        LegalExpertProfile legalExpertProfile = new LegalExpertProfile();
        BeanUtils.copyProperties(this, legalExpertProfile, "userId");
        if (this.userId != null) {
            legalExpertProfile.setUserSnapshot(UserSnapshot.builder().id(this.userId).build());
        }
        return legalExpertProfile;
    }
}