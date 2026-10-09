package es.upm.miw.apaw.domain.model.evidencemanagement;

import es.upm.miw.apaw.domain.model.UserSnapshot;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class CustodyRecord {

    @EqualsAndHashCode.Include
    private UUID id;

    private LocalDateTime recordedAt;

    private Integer durationMinutes;

    @NotBlank
    private String action;

    private String location;

    private String notes;

    @NotNull
    private UserSnapshot custodian;

    public void doDefault() {
        this.id = UUID.randomUUID();
        this.recordedAt = LocalDateTime.now();
    }

    public CustodyRecord ofSummary() {
        return CustodyRecord.builder()
                .id(this.id)
                .recordedAt(this.recordedAt)
                .durationMinutes(this.durationMinutes)
                .action(this.action)
                .location(this.location)
                .notes(this.notes)
                .custodian(UserSnapshot.builder()
                        .id(this.custodian.getId())
                        .mobile(this.custodian.getMobile())
                        .firstName(this.custodian.getFirstName())
                        .build())
                .build();
    }
}