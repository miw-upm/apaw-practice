package es.upm.miw.apaw.domain.exceptions;

public class ClientBusinessException extends RuntimeException {
    public ClientBusinessException(String userMessage) {
        super(userMessage);
    }
}



