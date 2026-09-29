package es.upm.miw.apaw.domain.model.contract;

import java.time.LocalDate;

public record ClauseUpdate(
        ClauseType type,
        String notes,
        LocalDate effectiveUntil
) {

}