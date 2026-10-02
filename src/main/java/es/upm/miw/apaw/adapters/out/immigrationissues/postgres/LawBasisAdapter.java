package es.upm.miw.apaw.adapters.out.immigrationissues.postgres;

import es.upm.miw.apaw.domain.model.immigrationissues.LawBasis;
import es.upm.miw.apaw.domain.ports.out.immigrationissues.LawBasisGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class LawBasisAdapter implements LawBasisGateway {

    private final LawBasisRepository lawBasisRepository;

    @Override
    public LawBasis create(LawBasis lawBasis) {
        return this.lawBasisRepository
                .save(new LawBasisEntity(lawBasis))
                .toDomain();
    }

    @Override
    public boolean existsByLawCode(String lawCode) {
        return this.lawBasisRepository.existsByLawCode(lawCode);
    }

    @Override
    public Optional<LawBasis> read(UUID id) {
        return this.lawBasisRepository.findById(id)
                .map(LawBasisEntity::toDomain);
    }
}