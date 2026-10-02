package es.upm.miw.apaw.adapters.out.copyright.postgres;

import es.upm.miw.apaw.domain.model.copyright.CreativeWork;
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

    @Override
    public boolean existsByRegistrationCode(String registrationCode) {
        return this.creativeWorkRepository.existsByRegistrationCode(registrationCode);
    }

    @Override
    public CreativeWork create(CreativeWork creativeWork) {
        CreativeWorkEntity creativeWorkEntity = new CreativeWorkEntity(creativeWork);
        this.creativeWorkRepository.save(creativeWorkEntity);
        return creativeWork;
    }
}
