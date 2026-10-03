package es.upm.miw.apaw.adapters.out.euregulation.postgres;

import es.upm.miw.apaw.domain.model.euregulation.EURegulation;
import es.upm.miw.apaw.domain.ports.out.euregulation.EURegulationGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class EURegulationAdapter implements EURegulationGateway {

    private final EURegulationRepository euRegulationRepository;

    @Override
    public Integer findMaxSequentialId() {
        return this.euRegulationRepository.findMaxSequentialId();
    }

    @Override
    @Transactional
    public EURegulation create(EURegulation euRegulation) {
        EURegulationEntity euRegulationEntity = new EURegulationEntity(euRegulation);
        if (euRegulationEntity.getSequentialId() == null
                || this.euRegulationRepository.existsBySequentialId(euRegulationEntity.getSequentialId())) {
            euRegulationEntity.setSequentialId(this.getNextSequentialId());
        }
        return this.euRegulationRepository.save(euRegulationEntity).toDomain();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<EURegulation> read(UUID id) {
        return this.euRegulationRepository.findById(id)
                .map(EURegulationEntity::toDomain);
    }

    @Override
    @Transactional(readOnly = true)
    public List<EURegulation> findAll() {
        return this.euRegulationRepository.findAllByOrderByRegulationNameAsc().stream()
                .map(EURegulationEntity::toDomain)
                .toList();
    }

    private Integer getNextSequentialId() {
        Integer highestSequentialId = this.euRegulationRepository.findMaxSequentialId();
        return highestSequentialId == null ? 1 : Math.addExact(highestSequentialId, 1);
    }
}
