package es.upm.miw.apaw.domain.exceptions;

public class NotFoundException extends ApiException {
    private static final String DESCRIPTION = "Not Found Exception";

    public NotFoundException(String detail) {
        super(DESCRIPTION, detail);
    }

    public NotFoundException(String detail, Throwable cause) {
        super(DESCRIPTION, detail, cause);
    }
}
