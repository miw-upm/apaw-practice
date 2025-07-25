package es.upm.miw.apaw.adapters.rest.exceptionshandler;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ErrorMessage {

    private final String error;

    private final String message;

    public ErrorMessage(Exception exception) {
        this.error = exception.getClass().getSimpleName();
        this.message = exception.getMessage();
    }

}
