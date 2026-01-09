package es.upm.miw.apaw.domain.services.clothingstore;

import es.upm.miw.apaw.domain.models.clothingstore.Garment;
import es.upm.miw.apaw.domain.persistenceports.clothingstore.GarmentPersistence;
import org.springframework.stereotype.Service;
import es.upm.miw.apaw.domain.models.UserDto;
import es.upm.miw.apaw.domain.restclients.UserRestClient;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.UUID;
import java.util.stream.Stream;
import java.util.List;

@Service
public class GarmentService {

    private final GarmentPersistence garmentPersistence;
    private final UserRestClient userRestClient;

    @Autowired
    public GarmentService(GarmentPersistence garmentPersistence, UserRestClient userRestClient) {
        this.garmentPersistence = garmentPersistence;
        this.userRestClient = userRestClient;
    }

    public Garment create(Garment garment) {
        return this.garmentPersistence.create(garment);
    }
    public Stream<Garment> findByPriceBetween(BigDecimal min, BigDecimal max) {
        return this.garmentPersistence.findByPriceBetween(min, max);
    }
    public Garment update(UUID id, Garment garment) {
        return this.garmentPersistence.update(id, garment);
    }
    public Stream<Garment> readAll() {
        return this.garmentPersistence.readAll();
    }
    public void delete(UUID id) {
        this.garmentPersistence.delete(id);
    }

    public BigDecimal sumDistinctPriceByMobile(String mobile) {
        UserDto user = this.userRestClient.readByMobile(mobile);
        UUID userId = user.getId();
        return this.garmentPersistence.sumDistinctPriceByUserId(userId);
    }

    public List<UUID> findDistinctIdsByInvoiceNumber(String number) {
        return this.garmentPersistence.findDistinctIdsByInvoiceNumber(number).toList();
    }

}




