package es.upm.miw.apaw.adapters.in.euregulation;

import es.upm.miw.apaw.domain.model.euregulation.ApplicationArea;
import es.upm.miw.apaw.domain.model.euregulation.EURegulation;
import es.upm.miw.apaw.domain.model.euregulation.IssuingBody;
import es.upm.miw.apaw.domain.model.euregulation.LegalInstrumentType;
import es.upm.miw.apaw.domain.model.euregulation.LegalStatus;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record EURegulationCreationDto(
        @NotNull String regulationName,
        @NotNull String officialReferenceNumber,
        @NotNull LegalInstrumentType instrumentType,
        @NotNull ApplicationArea applicationArea,
        @NotNull LegalStatus legalStatus,
        @NotNull IssuingBody issuingBody,
        LocalDate transpositionDeadline,
        String officialJournalLink,
        String summary
) {
    public EURegulation toDomain() {
        return EURegulation.builder()
                .regulationName(this.regulationName)
                .officialReferenceNumber(this.officialReferenceNumber)
                .instrumentType(this.instrumentType)
                .applicationArea(this.applicationArea)
                .legalStatus(this.legalStatus)
                .issuingBody(this.issuingBody)
                .transpositionDeadline(this.transpositionDeadline)
                .officialJournalLink(this.officialJournalLink)
                .summary(this.summary)
                .build();
    }
}
