package es.upm.miw.apaw.domain.ports.out.copyright;

import java.util.UUID;

public interface CreativeWorkGateway {
    boolean existsById(UUID id);
}
