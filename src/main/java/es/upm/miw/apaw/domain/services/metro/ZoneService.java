package es.upm.miw.apaw.domain.services.metro;

import es.upm.miw.apaw.domain.exceptions.ConflictException;
import es.upm.miw.apaw.domain.models.metro.Zone;
import es.upm.miw.apaw.domain.persistenceports.metro.ZonePersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Stream;

@Service
public class ZoneService {

    private final ZonePersistence zonePersistence;

    @Autowired
    public ZoneService(ZonePersistence zonePersistence) {
        this.zonePersistence = zonePersistence;
    }

    public Zone update(String type, Zone zone) {
        var existingZone = this.zonePersistence.getByType(type);

        if (!existingZone.getType().equals(zone.getType())) {
            this.assertTypeNotExists(zone.getType());
        }

        existingZone.setType(zone.getType());
        existingZone.setTicketPrice(zone.getTicketPrice());
        return this.zonePersistence.update(type, existingZone);
    }

    private void assertTypeNotExists(String type) {
        if (this.zonePersistence.existType(type)) {
            throw new ConflictException("Type already exists: " + type);
        }
    }

    public void updateTicketPrices(Stream<Zone> zonesList) {
        zonesList.map(zoneNewPrice -> {
                    Zone zone = this.zonePersistence.getByType(zoneNewPrice.getType());
                    zone.setTicketPrice(zoneNewPrice.getTicketPrice());
                    return zone;
                })
                .forEach(zone -> this.zonePersistence.update(zone.getType(), zone));
    }
}