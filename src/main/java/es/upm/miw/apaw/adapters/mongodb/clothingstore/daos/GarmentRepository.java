package es.upm.miw.apaw.adapters.mongodb.clothingstore.daos;

import es.upm.miw.apaw.adapters.mongodb.clothingstore.entities.GarmentEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public interface GarmentRepository extends MongoRepository<GarmentEntity, UUID> {

    // 用 $gte/$lte 明确写死范围查询（含边界）
    @Query("{ 'price': { $gte: ?0, $lte: ?1 } }")
    List<GarmentEntity> findByPriceBetween(BigDecimal min, BigDecimal max);
}