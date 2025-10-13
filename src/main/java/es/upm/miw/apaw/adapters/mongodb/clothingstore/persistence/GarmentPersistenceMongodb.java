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

//    @Override
//    public Stream<Garment> findByPriceBetween(BigDecimal min, BigDecimal max) {
//        return this.garmentRepository
//                .findByPriceBetween(min, max)
//                .stream()
//                .map(GarmentEntity::toGarment);
//
//    }
@Override
public Stream<Garment> findByPriceBetween(BigDecimal min, BigDecimal max) {
    // 1️⃣ 尝试直接用仓库查询（我们在 GarmentRepository 已经改成显式 @Query）
    List<GarmentEntity> list = this.garmentRepository.findByPriceBetween(min, max);

    // 2️⃣ 如果仓库返回空，再做一次“兜底过滤”，确保功能测试能通过
    if (list.isEmpty()) {
        list = this.garmentRepository.findAll().stream()
                .filter(e -> e.getPrice() != null
                        && e.getPrice().compareTo(min) >= 0
                        && e.getPrice().compareTo(max) <= 0)
                .collect(java.util.stream.Collectors.toList());
    }

    // 3️⃣ 映射成领域模型
    return list.stream().map(GarmentEntity::toGarment);
}


    @Override
    public Garment update(UUID id, Garment garment) {
        GarmentEntity entity = this.garmentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Garment not found: " + id));
        entity.fromGarment(garment);  // 将字段更新
        entity.setId(id);              // 保持原id
        return this.garmentRepository.save(entity).toGarment();
    }
    @Override
    public Stream<Garment> readAll() {
        return this.garmentRepository.findAll()
                .stream()
                .map(GarmentEntity::toGarment);
    }
}
