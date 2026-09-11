package es.upm.miw.apaw.domain.exceptions;

public class ForbiddenException extends ApiException {
    private static final String DESCRIPTION = "Forbidden Exception";

    public ForbiddenException(String detail) {
        super(DESCRIPTION, detail);
    }

    public ForbiddenException(String detail, Throwable cause) {
        super(DESCRIPTION, detail, cause);
    }
}
