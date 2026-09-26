package es.upm.miw.apaw.domain.model.euregulation;

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
public class EURegulation {

    @EqualsAndHashCode.Include
    private UUID id;

    @NotBlank
    private String regulationName;

    @NotNull
    private Long sequentialId;

    @NotBlank
    private String officialReferenceNumber;

    @NotNull
    private LegalInstrumentType instrumentType;

    @NotNull
    private ApplicationArea applicationArea;

    @NotNull
    private LegalStatus legalStatus;

    @NotNull
    private LocalDate entryIntoForceDate;

    private IssuingBody issuingBody;

    private LocalDate transpositionDeadline;

    private String officialJournalLink;

    private String summary;

    public void doDefault(Long highestSequentialId) {
        this.id = UUID.randomUUID();
        this.sequentialId = highestSequentialId == null ? 1 : Math.addExact(highestSequentialId, 1);
        if (this.entryIntoForceDate == null) {
            this.entryIntoForceDate = LocalDate.now();
        }
    }
}
