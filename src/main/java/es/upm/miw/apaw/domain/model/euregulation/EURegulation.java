package es.upm.miw.apaw.domain.model.euregulation;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class EURegulation {

    @EqualsAndHashCode.Include
    private UUID id;

    @NotNull
    private String regulationName;

    @NotNull
    private Integer sequentialId;

    @NotNull
    private String officialReferenceNumber;

    @NotNull
    private LegalInstrumentType instrumentType;

    @NotNull
    private ApplicationArea applicationArea;

    @NotNull
    private LegalStatus legalStatus;

    @NotNull
    private LocalDate entryIntoForceDate;

    @NotNull
    private IssuingBody issuingBody;

    private LocalDate transpositionDeadline;

    private String officialJournalLink;

    private String summary;

    public void doDefault(Integer highestSequentialId) {
        this.id = UUID.randomUUID();
        this.sequentialId = highestSequentialId == null ? 1 : Math.addExact(highestSequentialId, 1);
        if (this.entryIntoForceDate == null) {
            this.entryIntoForceDate = LocalDate.now();
        }
    }
}
