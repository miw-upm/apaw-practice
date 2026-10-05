package es.upm.miw.apaw.domain.model.stucktaskdetector;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StuckTaskAlertFindCriteria {

    private String procedureKeyword;

    private Boolean withPenalty;

    private Boolean escalated;

    private String creatorEmail;

    public boolean hasProcedureKeyword() {
        return this.procedureKeyword != null && !this.procedureKeyword.isBlank();
    }

    public boolean hasWithPenalty() {
        return this.withPenalty != null;
    }

    public boolean hasEscalated() {
        return this.escalated != null;
    }

    public boolean hasCreatorEmail() {
        return this.creatorEmail != null && !this.creatorEmail.isBlank();
    }
}
