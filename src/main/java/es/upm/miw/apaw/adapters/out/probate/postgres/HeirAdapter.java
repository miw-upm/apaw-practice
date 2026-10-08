package es.upm.miw.apaw.adapters.out.probate.postgres;

import es.upm.miw.apaw.domain.model.probate.Heir;
import es.upm.miw.apaw.domain.ports.out.probate.HeirGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class HeirAdapter implements HeirGateway {
    private final HeirRepository heirRepository;
    private final EstateRepository estateRepository;

    @Override
    public Heir create(Heir heir) {
        return this.heirRepository.save(new HeirEntity(heir)).toDomain();
    }

    @Override
    public List<Heir> findAll() {
        return this.heirRepository.findAllByOrderByNationalIdAsc().stream()
                .map(HeirEntity::toDomain)
                .toList();
    }

    @Override
    public Optional<Heir> read(UUID id) {
        return this.heirRepository.findById(id).map(HeirEntity::toDomain);
    }

    @Override
    public Heir update(Heir heir) {
        return this.heirRepository.save(new HeirEntity(heir)).toDomain();
    }

    @Override
    public void delete(UUID id) {
        this.heirRepository.deleteById(id);
    }

    @Override
    public boolean isUsedByEstate(UUID id) {
        return this.estateRepository.existsByHeirsId(id);
    }

    @Override
    public boolean existsByNationalId(String nationalId) {
        return this.heirRepository.existsByNationalId(nationalId);
    }
}