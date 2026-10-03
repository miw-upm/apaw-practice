package es.upm.miw.apaw.domain.services.courthearing;

import es.upm.miw.apaw.domain.exceptions.ConflictException;
import es.upm.miw.apaw.domain.model.courthearing.Court;
import es.upm.miw.apaw.domain.model.courthearing.CourtHearingByCourtReport;
import es.upm.miw.apaw.domain.model.courthearing.CourtUpdate;
import es.upm.miw.apaw.domain.ports.out.courthearing.CourtGateway;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import es.upm.miw.apaw.domain.exceptions.NotFoundException;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CourtService {
    private final CourtGateway courtGateway;

    public Court create(Court court) {
        this.assertNameNotExists(court.getName());
        this.assertPhoneNotExists(court.getPhone());
        court.doDefault();
        return this.courtGateway.create(court);
    }

    public Court read(UUID id) {
        return this.courtGateway.read(id)
                .orElseThrow(() -> new NotFoundException("Court id not found: " + id));
    }

    private void assertNameNotExists(String name) {
        if (this.courtGateway.existsByName(name)) {
            throw new ConflictException("Court name already exists: " + name);
        }
    }

    private void assertPhoneNotExists(String phone) {
        if (phone != null && this.courtGateway.existsByPhone(phone)) {
            throw new ConflictException("Court phone already exists: " + phone);
        }
    }

    public Court update(UUID id, Court update) {
        Court storedCourt = this.read(id);
        this.assertUniqueOnUpdate(storedCourt, update);
        storedCourt.setName(update.getName());
        storedCourt.setAddress(update.getAddress());
        storedCourt.setCity(update.getCity());
        storedCourt.setPhone(update.getPhone());
        storedCourt.setOpeningTime(update.getOpeningTime());
        storedCourt.setClosingTime(update.getClosingTime());
        storedCourt.setType(update.getType());
        return this.courtGateway.update(storedCourt);
    }

    private void assertUniqueOnUpdate(Court storedCourt, Court update) {
        if (!storedCourt.getName().equals(update.getName())) {
            this.assertNameNotExists(update.getName());
        }
        if (!Objects.equals(storedCourt.getPhone(), update.getPhone())) {
            this.assertPhoneNotExists(update.getPhone());
        }
    }

    public void delete(UUID id) {
        if (this.courtGateway.isReferenced(id)) {
            throw new ConflictException("Court is referenced by a court hearing: " + id);
        }
        this.courtGateway.delete(id);
    }


    public List<Court> findAll() {
        return this.courtGateway.findAll();
    }

    public Court patch(UUID id, CourtUpdate patch) {
        Court storedCourt = this.read(id);
        this.assertUniqueOnPatch(storedCourt, patch);
        Optional.ofNullable(patch.name()).ifPresent(storedCourt::setName);
        Optional.ofNullable(patch.address()).ifPresent(storedCourt::setAddress);
        Optional.ofNullable(patch.city()).ifPresent(storedCourt::setCity);
        Optional.ofNullable(patch.phone()).ifPresent(storedCourt::setPhone);
        Optional.ofNullable(patch.openingTime()).ifPresent(storedCourt::setOpeningTime);
        Optional.ofNullable(patch.closingTime()).ifPresent(storedCourt::setClosingTime);
        Optional.ofNullable(patch.type()).ifPresent(storedCourt::setType);
        return this.courtGateway.update(storedCourt);
    }

    private void assertUniqueOnPatch(Court storedCourt, CourtUpdate patch) {
        if (patch.name() != null && !patch.name().equals(storedCourt.getName())) {
            this.assertNameNotExists(patch.name());
        }
        if (patch.phone() != null && !patch.phone().equals(storedCourt.getPhone())) {
            this.assertPhoneNotExists(patch.phone());
        }
    }

    public List<CourtHearingByCourtReport> findHearingByCourtReport() {
        return this.courtGateway.findHearingByCourtReport();
    }
}