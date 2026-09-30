package es.upm.miw.apaw.domain.model.evidencemanagement;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Evidence {

    @EqualsAndHashCode.Include
    private UUID id;

    @NotBlank
    private String title;

    private String description;

    @NotNull
    private EvidenceType evidenceType;

    private EvidenceStatus status;

    private LocalDateTime collectionDate;

    private String source;

    private Boolean confidential;

    private List<CustodyRecord> custodyRecords;

    public void doDefault() {
        this.id = UUID.randomUUID();
        if (this.status == null) {
            this.status = EvidenceStatus.REGISTERED;
        }
        if (this.confidential == null) {
            this.confidential = false;
        }
    }
}
