package es.upm.miw.apaw.adapters.out.copyright.postgres;

import es.upm.miw.apaw.domain.ports.out.copyright.CreativeWorkGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class CreativeWorkAdapter implements CreativeWorkGateway {
    private final CreativeWorkRepository creativeWorkRepository;

    @Override
    public boolean existsById(UUID id) {
        return this.creativeWorkRepository.existsById(id);
    }
}
