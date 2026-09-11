package es.upm.miw.apaw.domain.exceptions;

import lombok.Getter;

@Getter
public abstract class ApiException extends RuntimeException {
    private final String detail;
    private final String causeDetail;

    protected ApiException(String description, String detail) {
        super(description + ". " + detail);
        this.detail = detail;
        this.causeDetail = "";
    }

    protected ApiException(String description, String detail, Throwable cause) {
        super(description + ". " + detail, cause);
        this.detail = detail;
        this.causeDetail = buildCauseDetail(cause);
    }

    private String buildCauseDetail(Throwable throwable) {
        if (throwable == null) {
            return "";
        }
        StringBuilder current = new StringBuilder(throwable.getClass().getSimpleName());
        if (throwable.getMessage() != null && !throwable.getMessage().isBlank()) {
            current.append(": ").append(throwable.getMessage());
        }
        if (throwable instanceof ApiException apiException && !apiException.getCauseDetail().isEmpty()) {
            current.append(" -> ").append(apiException.getCauseDetail());
        } else if (throwable.getCause() != null) {
            String nestedCause = buildCauseDetail(throwable.getCause());
            if (!nestedCause.isEmpty()) {
                current.append(" -> ").append(nestedCause);
            }
        }
        return current.toString();
    }
}
