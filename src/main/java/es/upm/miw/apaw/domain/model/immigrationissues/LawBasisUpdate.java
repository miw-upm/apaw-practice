package es.upm.miw.apaw.domain.model.immigrationissues;

import java.time.LocalDate;

public record LawBasisUpdate(
        String lawCode,
        String lawName,
        Integer articleNumber,
        LocalDate publishedOn,
        Boolean active
) {
}