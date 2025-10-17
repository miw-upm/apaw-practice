package es.upm.miw.apaw.domain.services.vehicle;

import es.upm.miw.apaw.domain.models.vehicle.Extra;
import es.upm.miw.apaw.domain.persistenceports.vehicle.ExtraPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.stream.Stream;

@Service
public class ExtraService {

    private final ExtraPersistence extraPersistence;

    @Autowired
    public ExtraService(ExtraPersistence extraPersistence) {
        this.extraPersistence = extraPersistence;
    }

    public void updatePrices(Stream<Extra> extrasList) {
        extrasList.map(extraNewPrice -> {
                    Extra extra = this.extraPersistence.readByCategoryAndDescription(extraNewPrice.getCategory(), extraNewPrice.getDescription());
                    extra.setPrice(extraNewPrice.getPrice());
                    return extra;
                })
                .forEach(this.extraPersistence::update);
    }

    public void delete(UUID id) {
        this.extraPersistence.delete(id);
    }
}
