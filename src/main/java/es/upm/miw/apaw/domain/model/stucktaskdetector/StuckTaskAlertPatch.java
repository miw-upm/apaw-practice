package es.upm.miw.apaw.domain.model.stucktaskdetector;

import java.time.LocalDate;

public record StuckTaskAlertPatch(
        String reference,
        LocalDate resolvedAt,
        Boolean escalated,
        String resolutionNotes
) {
}
