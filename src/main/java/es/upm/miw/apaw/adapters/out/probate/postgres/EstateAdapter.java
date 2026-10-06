package es.upm.miw.apaw.adapters.out.probate.postgres;

import es.upm.miw.apaw.domain.model.probate.Estate;
import es.upm.miw.apaw.domain.ports.out.probate.EstateGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class EstateAdapter implements EstateGateway {
    private final EstateRepository estateRepository;
    private final HeirRepository heirRepository;

    @Override
    @Transactional
    public Estate create(Estate estate) {
        EstateEntity entity = new EstateEntity(estate);
        entity.setHeirs(estate.getHeirs().stream()
                .map(heir -> this.heirRepository.getReferenceById(heir.getId()))
                .collect(Collectors.toCollection(ArrayList::new)));
        this.estateRepository.save(entity);
        return estate;
    }

    @Override
    public boolean existsByFileNumber(String fileNumber) {
        return this.estateRepository.existsByFileNumber(fileNumber);
    }
}
