package es.upm.miw.apaw_practice.domain.services.gun_store;

import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.models.gun_store.Accesory;
import es.upm.miw.apaw_practice.domain.models.gun_store.Setup;
import es.upm.miw.apaw_practice.domain.persistence_ports.gun_store.SetupPersistence;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Comparator;

@Service
public class SetupService {

    private final SetupPersistence setupPersistence;

    public SetupService(SetupPersistence setupPersistence) {
        this.setupPersistence = setupPersistence;
    }

    public void delete(Integer id) {
        this.setupPersistence.delete(id);
    }

    public Setup read(Integer id) {
        return this.setupPersistence.read(id);
    }

    public Setup create(Setup setup) {
        setup.setOrderDate(LocalDate.now());
        return this.setupPersistence.create(setup);
    }

    public Setup findMostExpensiveByCaliberAndCategory(String caliber, String category) {
        return this.setupPersistence.findAll()
                .filter(
                        setup -> setup.getGuns().stream()
                                .map(gun -> gun.getAmmo().getCaliber()
                                ).anyMatch(cal -> cal.matches(caliber))
                )
                .filter(
                        setup -> setup.getGuns().stream()
                                .flatMap(gun -> gun.getAccesories().stream())
                                .map(Accesory::getCategory)
                                .anyMatch(cat -> cat.matches(category))
                )
                .max(Comparator.comparing(Setup::getTotalPrice))
                .orElseThrow(() -> new NotFoundException("No setup found for given search criteria"));
    }
}
