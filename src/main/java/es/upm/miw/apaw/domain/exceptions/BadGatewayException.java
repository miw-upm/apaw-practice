package es.upm.miw.apaw.domain.exceptions;

public class BadGatewayException extends ApiException {
    private static final String DESCRIPTION = "Bad Gateway Exception";

    public BadGatewayException(String detail) {
        super(DESCRIPTION, detail);
    }

    public BadGatewayException(String detail, Throwable cause) {
        super(DESCRIPTION, detail, cause);
    }
}
