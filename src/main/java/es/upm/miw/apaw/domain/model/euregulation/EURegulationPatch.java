package es.upm.miw.apaw.domain.model.euregulation;

import jakarta.validation.constraints.Pattern;

import java.time.LocalDate;

public record EURegulationPatch(
        @Pattern(regexp = ".*\\S.*") String regulationName,
        @Pattern(regexp = ".*\\S.*") String officialReferenceNumber,
        LegalInstrumentType instrumentType,
        ApplicationArea applicationArea,
        LegalStatus legalStatus,
        IssuingBody issuingBody,
        LocalDate transpositionDeadline,
        String officialJournalLink,
        String summary
) {
}
