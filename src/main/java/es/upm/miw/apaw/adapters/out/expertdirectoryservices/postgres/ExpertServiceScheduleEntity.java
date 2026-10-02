package es.upm.miw.apaw.adapters.out.expertdirectoryservices.postgres;

import es.upm.miw.apaw.domain.model.expertdirectoryservices.ExpertServiceSchedule;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ExpertServiceScheduleEntity {

    @Id
    @EqualsAndHashCode.Include
    private UUID id;

    @Column(nullable = false, unique = true)
    private String tariffCode;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private BigDecimal rateAmount;

    @Column(nullable = false)
    private String currency;

    private String specialCondition;

    @Column(nullable = false)
    private LocalDate creationDate;

    @OneToMany
    @JoinColumn(name = "expert_service_schedule_id", nullable = false)
    private List<LegalExpertProfileEntity> legalExpertProfiles;

    public ExpertServiceScheduleEntity(ExpertServiceSchedule expertServiceSchedule) {
        BeanUtils.copyProperties(expertServiceSchedule, this, "legalExpertProfiles");
        if (expertServiceSchedule.getLegalExpertProfiles() != null) {
            this.legalExpertProfiles = expertServiceSchedule.getLegalExpertProfiles().stream()
                    .map(LegalExpertProfileEntity::new)
                    .toList();
        } else {
            this.legalExpertProfiles = new ArrayList<>();
        }
    }

    public ExpertServiceSchedule toDomain() {
        ExpertServiceSchedule expertServiceSchedule = new ExpertServiceSchedule();
        BeanUtils.copyProperties(this, expertServiceSchedule, "legalExpertProfiles");
        if (this.legalExpertProfiles != null) {
            expertServiceSchedule.setLegalExpertProfiles(new ArrayList<>(this.legalExpertProfiles.stream()
                    .map(LegalExpertProfileEntity::toDomain)
                    .toList()));
        }
        return expertServiceSchedule;
    }
}