package es.upm.miw.apaw.domain.services.courthearing;

import es.upm.miw.apaw.domain.exceptions.ConflictException;
import es.upm.miw.apaw.domain.model.courthearing.Court;
import es.upm.miw.apaw.domain.ports.out.courthearing.CourtGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import es.upm.miw.apaw.domain.exceptions.NotFoundException;
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
}