package es.upm.miw.apaw.adapters.out.euregulation.postgres;

import es.upm.miw.apaw.domain.model.euregulation.EURegulation;
import es.upm.miw.apaw.domain.ports.out.euregulation.EURegulationGateway;
import jakarta.validation.Valid;
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
    public boolean existsByOfficialReferenceNumber(String officialReferenceNumber) {
        return this.euRegulationRepository.existsByOfficialReferenceNumber(officialReferenceNumber);
    }

    @Override
    public boolean existsBySequentialId(Integer sequentialId) {
        return this.euRegulationRepository.existsBySequentialId(sequentialId);
    }

    @Override
    @Transactional
    public EURegulation create(@Valid EURegulation euRegulation) {
        EURegulationEntity euRegulationEntity = new EURegulationEntity(euRegulation);
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

}
