package es.upm.miw.apaw.domain.services.probate;

import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.model.probate.CreationEstate;
import es.upm.miw.apaw.domain.model.probate.Estate;
import es.upm.miw.apaw.domain.model.probate.Heir;
import es.upm.miw.apaw.domain.ports.out.probate.EstateGateway;
import es.upm.miw.apaw.domain.ports.out.probate.HeirGateway;
import es.upm.miw.apaw.domain.ports.out.user.UserFinder;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EstateService {
    private final EstateGateway estateGateway;
    private final HeirGateway heirGateway;
    private final UserFinder userFinder;

    public Estate create(CreationEstate creation) {
        Estate estate = new Estate();
        BeanUtils.copyProperties(creation, estate);
        estate.setHeirs(creation.getHeirIds().stream()
                .map(this::readHeir)
                .toList());
        estate.setUserSnapshot(this.userFinder.read(creation.getUserId()));
        return this.estateGateway.create(estate);
    }

    private Heir readHeir(UUID id) {
        return this.heirGateway.read(id)
                .orElseThrow(() -> new NotFoundException("Heir id not found: " + id));
    }
}
