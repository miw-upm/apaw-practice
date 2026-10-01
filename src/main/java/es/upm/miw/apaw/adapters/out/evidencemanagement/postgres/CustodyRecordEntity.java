package es.upm.miw.apaw.adapters.out.evidencemanagement.postgres;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

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
}
