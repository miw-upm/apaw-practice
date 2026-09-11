package es.upm.miw.apaw.domain.exceptions;

public class InternalServerException extends ApiException {
    private static final String DESCRIPTION = "Internal Server Exception";

    public InternalServerException(String detail) {
        super(DESCRIPTION, detail);
    }

    public InternalServerException(String detail, Throwable cause) {
        super(DESCRIPTION, detail, cause);
    }
}
