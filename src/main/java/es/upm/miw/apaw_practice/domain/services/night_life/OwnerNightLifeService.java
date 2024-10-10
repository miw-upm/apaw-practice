package es.upm.miw.apaw_practice.domain.services.night_life;
import es.upm.miw.apaw_practice.domain.models.night_life.Owner;
import es.upm.miw.apaw_practice.domain.models.night_life.Reservation;
import es.upm.miw.apaw_practice.domain.persistence_ports.night_life.ClubPersistence;
import es.upm.miw.apaw_practice.domain.persistence_ports.night_life.OwnerNightLifePersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;

@Service
public class OwnerNightLifeService {
    private final OwnerNightLifePersistence ownerPersistence;
    private final ClubPersistence clubPersistence;
    @Autowired
    public OwnerNightLifeService(OwnerNightLifePersistence ownerPersistence, ClubPersistence clubPersistence) {
        this.ownerPersistence = ownerPersistence;
        this.clubPersistence = clubPersistence;
    }
    public Owner create(Owner owner) {
        return this.ownerPersistence.create(owner);
    }

    public Owner readByName(String name) {
        return this.ownerPersistence.readByName(name)
                .orElseThrow(() -> new RuntimeException("Owner not found: " + name));
    }

    public BigDecimal calculateTotalPriceByOwner(String name) {
        Owner owner = this.readByName(name);
        return this.clubPersistence.readAll()
                .filter(club -> club.getOwner().getName().equals(owner.getName()))
                .flatMap(club -> club.getReservations().stream()
                        .map(Reservation::getPrice))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
