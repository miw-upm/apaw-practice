package es.upm.miw.apaw.domain.exceptions;

public class ConflictException extends ApiException {
    private static final String DESCRIPTION = "Conflict Exception";

    public ConflictException(String detail) {
        super(DESCRIPTION, detail);
    }

    public ConflictException(String detail, Throwable cause) {
        super(DESCRIPTION, detail, cause);
    }
}
