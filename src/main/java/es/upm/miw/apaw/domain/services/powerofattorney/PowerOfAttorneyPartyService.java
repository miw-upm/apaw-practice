package es.upm.miw.apaw.domain.services.powerofattorney;

import es.upm.miw.apaw.domain.exceptions.BadRequestException;
import es.upm.miw.apaw.domain.exceptions.ConflictException;
import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.model.UserSnapshot;
import es.upm.miw.apaw.domain.model.powerofattorney.CreationPowerOfAttorneyParty;
import es.upm.miw.apaw.domain.model.powerofattorney.PowerOfAttorneyParty;
import es.upm.miw.apaw.domain.model.powerofattorney.PowerOfAttorneyPartyPatch;
import es.upm.miw.apaw.domain.model.powerofattorney.PowerOfAttorneyPartyReport;
import es.upm.miw.apaw.domain.ports.out.powerofattorney.PowerOfAttorneyGateway;
import es.upm.miw.apaw.domain.ports.out.powerofattorney.PowerOfAttorneyPartyGateway;
import es.upm.miw.apaw.domain.ports.out.user.UserFinder;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PowerOfAttorneyPartyService {

    private final PowerOfAttorneyPartyGateway powerOfAttorneyPartyGateway;
    private final PowerOfAttorneyGateway powerOfAttorneyGateway;
    private final UserFinder userFinder;

    public PowerOfAttorneyParty create(CreationPowerOfAttorneyParty party) {
        PowerOfAttorneyParty powerOfAttorneyParty = new PowerOfAttorneyParty();
        BeanUtils.copyProperties(party, powerOfAttorneyParty);
        powerOfAttorneyParty.setUserSnapshot(this.readUser(party.userId()));
        powerOfAttorneyParty.doDefault();
        return this.powerOfAttorneyPartyGateway.create(powerOfAttorneyParty);
    }

    private UserSnapshot readUser(UUID id) {
        return this.userFinder.read(id);
    }

    private PowerOfAttorneyParty findOne(UUID id){
        return this.powerOfAttorneyPartyGateway.read(id)
                .orElseThrow(() ->  new NotFoundException("Power of attorney party id not found: " + id));
    }

    public PowerOfAttorneyParty read(UUID id) {
        PowerOfAttorneyParty powerOfAttorneyParty = this.findOne(id);
        UUID userId = powerOfAttorneyParty.getUserSnapshot().getId();
        powerOfAttorneyParty.setUserSnapshot(this.readUser(userId));
        return powerOfAttorneyParty;
    }

    public PowerOfAttorneyParty update(UUID id, CreationPowerOfAttorneyParty party) {
        PowerOfAttorneyParty powerOfAttorneyParty = this.findOne(id);
        BeanUtils.copyProperties(party, powerOfAttorneyParty);
        powerOfAttorneyParty.setUserSnapshot(this.readUser(party.userId()));
        return this.powerOfAttorneyPartyGateway.update(powerOfAttorneyParty);
    }

    public void delete(UUID id) {
        if (this.powerOfAttorneyGateway.isReferenced(id)) {
            throw new ConflictException("Power of attorney party is referenced by a power of attorney: " + id);
        }
        this.powerOfAttorneyPartyGateway.delete(id);
    }

    public List<PowerOfAttorneyParty> findAll() {
        List<PowerOfAttorneyParty> parties = this.powerOfAttorneyPartyGateway.findAll();
        if (parties.isEmpty()) {
            return List.of();
        }
        Set<UUID> userIds = parties.stream()
                .map(party -> party.getUserSnapshot().getId())
                .collect(Collectors.toSet());
        Map<UUID, UserSnapshot> users = this.userFinder.findByIds(userIds).stream()
                .collect(Collectors.toMap(UserSnapshot::getId, Function.identity()));
        for (PowerOfAttorneyParty party : parties) {
            UUID userId = party.getUserSnapshot().getId();
            UserSnapshot user = users.get(userId);
            if (user == null) {
                throw new NotFoundException("User id not found: " + userId);
            }
            party.setUserSnapshot(user);
        }
        return parties;
    }

    public List<PowerOfAttorneyPartyReport> findReport() {
        List<PowerOfAttorneyPartyReport> reports = this.powerOfAttorneyPartyGateway.findReport();
        if (reports.isEmpty()) {
            return List.of();
        }
        Set<UUID> userIds = reports.stream()
                .map(report -> report.getUserSnapshot().getId())
                .collect(Collectors.toSet());
        Map<UUID, UserSnapshot> users = this.userFinder.findByIds(userIds).stream()
                .collect(Collectors.toMap(UserSnapshot::getId, Function.identity()));
        reports.forEach(report -> {
            UUID userId = report.getUserSnapshot().getId();
            UserSnapshot user = users.get(userId);
            if (user == null) {
                throw new NotFoundException("User id not found: " + userId);
            }
            report.setUserSnapshot(user);
        });
        return reports;
    }

    public void patch(List<PowerOfAttorneyPartyPatch> patches) {
        this.assertUniqueIds(patches);
        patches.forEach(patch -> {
            if (patch.age() == null && patch.fullMentalCapacity() == null) {
                throw new BadRequestException(
                        "At least one of age or fullMentalCapacity is required: " + patch.id());
            }
            PowerOfAttorneyParty stored = this.powerOfAttorneyPartyGateway.read(patch.id())
                    .orElseThrow(() -> new NotFoundException(
                            "Power of attorney party id not found: " + patch.id()));
            if (patch.age() != null) {
                stored.setAge(patch.age());
            }
            if (patch.fullMentalCapacity() != null) {
                stored.setFullMentalCapacity(patch.fullMentalCapacity());
            }
            this.powerOfAttorneyPartyGateway.update(stored);
        });
    }

    private void assertUniqueIds(List<PowerOfAttorneyPartyPatch> patches) {
        Set<UUID> ids = new HashSet<>();
        for (PowerOfAttorneyPartyPatch patch : patches) {
            if (!ids.add(patch.id())) {
                throw new BadRequestException("Repeated power of attorney party id: " + patch.id());
            }
        }
    }
}
