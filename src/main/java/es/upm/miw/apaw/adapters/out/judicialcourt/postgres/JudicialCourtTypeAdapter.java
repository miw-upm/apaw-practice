package es.upm.miw.apaw.adapters.out.judicialcourt.postgres;

import es.upm.miw.apaw.domain.model.judicialcourt.JudicialCourtType;
import es.upm.miw.apaw.domain.ports.out.judicialcourt.JudicialCourtTypeGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class JudicialCourtTypeAdapter implements JudicialCourtTypeGateway {
    private final JudicialCourtTypeRepository judicialCourtTypeRepository;

    @Override
    public JudicialCourtType create(JudicialCourtType judicialCourtType) {
        return this.judicialCourtTypeRepository
                .save(new JudicialCourtTypeEntity(judicialCourtType))
                .toDomain();
    }

    @Override
    public boolean existsByName(String name) {
        return this.judicialCourtTypeRepository.existsByName(name);
    }

    @Override
    public boolean existsByCode(String code) {
        return this.judicialCourtTypeRepository.existsByCode(code);
    }
}
