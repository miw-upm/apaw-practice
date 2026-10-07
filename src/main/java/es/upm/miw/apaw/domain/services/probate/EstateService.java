package es.upm.miw.apaw.domain.services.probate;

import es.upm.miw.apaw.domain.exceptions.ConflictException;
import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.model.UserSnapshot;
import es.upm.miw.apaw.domain.model.probate.CreationEstate;
import es.upm.miw.apaw.domain.model.probate.Estate;
import es.upm.miw.apaw.domain.model.probate.EstateFindCriteria;
import es.upm.miw.apaw.domain.model.probate.EstateUsageReport;
import es.upm.miw.apaw.domain.model.probate.Heir;
import es.upm.miw.apaw.domain.ports.out.probate.EstateGateway;
import es.upm.miw.apaw.domain.ports.out.probate.HeirGateway;
import es.upm.miw.apaw.domain.ports.out.user.UserFinder;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EstateService {
    private final EstateGateway estateGateway;
    private final HeirGateway heirGateway;
    private final UserFinder userFinder;

    public Estate create(CreationEstate creation) {
        if (this.estateGateway.existsByFileNumber(creation.getFileNumber())) {
            throw new ConflictException("Estate fileNumber already exists: " + creation.getFileNumber());
        }
        Estate estate = new Estate();
        BeanUtils.copyProperties(creation, estate);
        estate.setHeirs(creation.getHeirIds().stream()
                .map(this::readHeir)
                .toList());
        estate.setUserSnapshot(this.userFinder.read(creation.getUserId()));
        estate.doDefault();
        return this.estateGateway.create(estate);
    }

    private Heir readHeir(UUID id) {
        return this.heirGateway.read(id)
                .orElseThrow(() -> new NotFoundException("Heir id not found: " + id));
    }

    public List<EstateUsageReport> findUsageReport() {
        return this.estateGateway.findUsageReport();
    }

    public List<Estate> find(EstateFindCriteria criteria) {
        List<Estate> estates = this.estateGateway.find(criteria);
        if (estates.isEmpty()) {
            return List.of();
        }
        Set<UUID> userIds = estates.stream()
                .map(estate -> estate.getUserSnapshot().getId())
                .collect(Collectors.toSet());
        Map<UUID, UserSnapshot> usersById = this.userFinder.findByIds(userIds).stream()
                .collect(Collectors.toMap(UserSnapshot::getId, Function.identity()));
        estates.forEach(estate -> {
            UUID userId = estate.getUserSnapshot().getId();
            UserSnapshot user = usersById.get(userId);
            if (user == null) {
                throw new NotFoundException("User id not found: " + userId);
            }
            estate.setUserSnapshot(user);
        });
        return estates.stream()
                .filter(estate -> !criteria.appliesUserMobile()
                        || criteria.getUserMobile().equals(estate.getUserSnapshot().getMobile()))
                .toList();
    }
}
