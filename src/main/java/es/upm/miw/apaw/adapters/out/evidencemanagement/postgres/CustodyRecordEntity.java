package es.upm.miw.apaw.adapters.out.evidencemanagement.postgres;

import es.upm.miw.apaw.domain.model.UserSnapshot;
import es.upm.miw.apaw.domain.model.evidencemanagement.CustodyRecord;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;
import org.springframework.beans.BeanUtils;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class CustodyRecordEntity {

    @Id
    @EqualsAndHashCode.Include
    private UUID id;

    @Column(nullable = false)
    private LocalDateTime recordedAt;

    private Integer durationMinutes;

    @Column(nullable = false)
    private String action;

    private String location;

    private String notes;

    @Column(nullable = false)
    private UUID custodianId;

    public CustodyRecordEntity(CustodyRecord custodyRecord) {
        BeanUtils.copyProperties(custodyRecord, this);
        this.custodianId = custodyRecord.getCustodian().getId();
    }

    public CustodyRecord toDomain() {
        CustodyRecord custodyRecord = new CustodyRecord();
        BeanUtils.copyProperties(this, custodyRecord);
        custodyRecord.setCustodian(UserSnapshot.builder().id(this.custodianId).build());
        return custodyRecord;
    }
}
