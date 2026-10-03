package es.upm.miw.apaw.adapters.out.probate.postgres;

import es.upm.miw.apaw.domain.model.probate.Heir;
import es.upm.miw.apaw.domain.ports.out.probate.HeirGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class HeirAdapter implements HeirGateway {
    private final HeirRepository heirRepository;

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
    public boolean existsByNationalId(String nationalId) {
        return this.heirRepository.existsByNationalId(nationalId);
    }
}