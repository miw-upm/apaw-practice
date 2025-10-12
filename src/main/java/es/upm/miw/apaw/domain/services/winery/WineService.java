package es.upm.miw.apaw.domain.services.winery;

import es.upm.miw.apaw.domain.models.winery.Wine;
import es.upm.miw.apaw.domain.persistenceports.winery.WinePersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.stream.Stream;

@Service
public class WineService {

    private final WinePersistence winePersistence;

    @Autowired
    public WineService(WinePersistence winePersistence) {
        this.winePersistence = winePersistence;
    }

    public Wine read(UUID id) {
        return this.winePersistence.readById(id);
    }

    public void delete(UUID id) {
        this.winePersistence.delete(id);
    }

    public void updatePrices(Stream<Wine> winesList) {
        winesList.map(wineNewPrice -> {
            Wine wine = this.winePersistence.readById(wineNewPrice.getId());
            wine.setPrice(wineNewPrice.getPrice());
            return wine;
        })
                .forEach(this.winePersistence::update);
    }
}
