package es.upm.miw.apaw.adapters.out.judicialcourt.postgres;

import es.upm.miw.apaw.domain.model.judicialcourt.JudicialCourtType;
import es.upm.miw.apaw.domain.ports.out.judicialcourt.JudicialCourtTypeGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class JudicialCourtTypeAdapter implements JudicialCourtTypeGateway {
    private final JudicialCourtTypeRepository judicialCourtTypeRepository;
    private final JudicialCourtRepository judicialCourtRepository;

    @Override
    public JudicialCourtType create(JudicialCourtType judicialCourtType) {
        return this.judicialCourtTypeRepository
                .save(new JudicialCourtTypeEntity(judicialCourtType))
                .toDomain();
    }

    @Override
    public List<JudicialCourtType> findAll() {
        return this.judicialCourtTypeRepository.findAllByOrderByNameAscCodeAsc().stream()
                .map(JudicialCourtTypeEntity::toDomain)
                .toList();
    }

    @Override
    public Optional<JudicialCourtType> read(UUID id) {
        return this.judicialCourtTypeRepository.findById(id)
                .map(JudicialCourtTypeEntity::toDomain);
    }

    @Override
    public JudicialCourtType update(JudicialCourtType judicialCourtType) {
        return this.judicialCourtTypeRepository
                .save(new JudicialCourtTypeEntity(judicialCourtType))
                .toDomain();
    }

    @Override
    public void delete(UUID id) {
        this.judicialCourtTypeRepository.deleteById(id);
    }

    @Override
    public boolean isReferenced(UUID id) {
        return this.judicialCourtRepository.existsByTypeId(id);
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
