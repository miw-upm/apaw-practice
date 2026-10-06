package es.upm.miw.apaw.adapters.out.probate.postgres;

import es.upm.miw.apaw.domain.model.probate.Estate;
import es.upm.miw.apaw.domain.ports.out.probate.EstateGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class EstateAdapter implements EstateGateway {
    private final EstateRepository estateRepository;

    @Override
    public Estate create(Estate estate) {
        return this.estateRepository.save(new EstateEntity(estate)).toDomain();
    }
}
