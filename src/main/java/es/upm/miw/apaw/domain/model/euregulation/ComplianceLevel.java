package es.upm.miw.apaw.domain.model.euregulation;

import lombok.Getter;

@Getter
public enum ComplianceLevel {
    COMPLIANT("Compliant"),
    PARTIALLY_COMPLIANT("PartiallyCompliant"),
    NON_COMPLIANT("NonCompliant"),
    PENDING_REVIEW("PendingReview"),
    DRAFT_REVIEW("DraftReview"),
    DEPRECATED("Deprecated");

    private final String value;

    ComplianceLevel(String value) {
        this.value = value;
    }
}
