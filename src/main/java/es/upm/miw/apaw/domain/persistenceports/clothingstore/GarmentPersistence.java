package es.upm.miw.apaw.domain.persistenceports.clothingstore;

import es.upm.miw.apaw.domain.models.clothingstore.Garment;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.stream.Stream;
import java.util.UUID;

@Repository
public interface GarmentPersistence {

    /** 按价格区间查询（闭区间） */
    Stream<Garment> findByPriceBetween(BigDecimal min, BigDecimal max);
    Garment update(UUID id, Garment garment);

    Stream<Garment> readAll();

}
