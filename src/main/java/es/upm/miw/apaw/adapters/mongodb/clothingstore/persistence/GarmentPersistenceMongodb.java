package es.upm.miw.apaw.adapters.mongodb.clothingstore.persistence;

import es.upm.miw.apaw.adapters.mongodb.clothingstore.daos.GarmentRepository;
import es.upm.miw.apaw.adapters.mongodb.clothingstore.entities.GarmentEntity;
import es.upm.miw.apaw.domain.models.clothingstore.Garment;
import es.upm.miw.apaw.domain.persistenceports.clothingstore.GarmentPersistence;
import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.UUID;
import java.util.stream.Stream;
import java.util.List;

@Repository
public class GarmentPersistenceMongodb implements GarmentPersistence {

    private final GarmentRepository garmentRepository;
    @Autowired
    public GarmentPersistenceMongodb(GarmentRepository garmentRepository) {
        this.garmentRepository = garmentRepository;
    }
@Override
public Stream<Garment> findByPriceBetween(BigDecimal min, BigDecimal max) {
    List<GarmentEntity> list = this.garmentRepository.findByPriceBetween(min, max);

    if (list.isEmpty()) {
        list = this.garmentRepository.findAll().stream()
                .filter(e -> e.getPrice() != null
                        && e.getPrice().compareTo(min) >= 0
                        && e.getPrice().compareTo(max) <= 0)
                .collect(java.util.stream.Collectors.toList());
    }

    return list.stream().map(GarmentEntity::toGarment);
}
    @Override
    public Garment update(UUID id, Garment garment) {
        GarmentEntity entity = this.garmentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Garment not found: " + id));
        entity.fromGarment(garment);
        entity.setId(id);
        return this.garmentRepository.save(entity).toGarment();
    }
    @Override
    public Stream<Garment> readAll() {
        return this.garmentRepository.findAll()
                .stream()
                .map(GarmentEntity::toGarment);
    }
}
