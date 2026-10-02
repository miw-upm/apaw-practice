package es.upm.miw.apaw.domain.ports.out.copyright;

import java.util.UUID;

import es.upm.miw.apaw.domain.model.copyright.CreativeWork;

public interface CreativeWorkGateway {
    boolean existsById(UUID id);
    boolean existsByRegistrationCode(String registrationCode);
    CreativeWork create(CreativeWork creativeWork);
}
