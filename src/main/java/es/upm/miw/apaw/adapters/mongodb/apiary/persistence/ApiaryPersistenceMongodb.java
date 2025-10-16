package es.upm.miw.apaw.adapters.mongodb.apiary.persistence;

import es.upm.miw.apaw.adapters.mongodb.apiary.daos.ApiaryRepository;
import es.upm.miw.apaw.adapters.mongodb.apiary.daos.SaleRepository;
import es.upm.miw.apaw.adapters.mongodb.apiary.entities.ApiaryEntity;
import es.upm.miw.apaw.adapters.mongodb.apiary.entities.HiveEntity;
import es.upm.miw.apaw.adapters.mongodb.apiary.entities.ProductEntity;
import es.upm.miw.apaw.adapters.mongodb.apiary.entities.SaleEntity;
import es.upm.miw.apaw.domain.models.apiary.Apiary;
import es.upm.miw.apaw.domain.persistenceports.apiary.ApiaryPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Repository("apiaryPersistence")
public class ApiaryPersistenceMongodb implements ApiaryPersistence {

    private final ApiaryRepository apiaryRepository;
    private final SaleRepository saleRepository;


    @Autowired
    public ApiaryPersistenceMongodb(ApiaryRepository apiaryRepository, SaleRepository saleRepository) {
        this.apiaryRepository = apiaryRepository;
        this.saleRepository = saleRepository;
    }

    @Override
    public Stream<Apiary> findByLocation(String location) {
        return this.apiaryRepository.findByLocation(location)
                .stream()
                .map(ApiaryEntity::toApiary);
    }

    @Override
    public Set<String> findLocationsByShippingAddress(String shippingAddress) {
        List<SaleEntity> sales = this.saleRepository.findAll()
                .stream()
                .filter(sale -> shippingAddress.equals(sale.getShippingAddress()))
                .toList();

        Set<String> productBarcodes = sales.stream()
                .flatMap(sale -> sale.getProductEntities().stream())
                .map(ProductEntity::getBarcode)
                .collect(Collectors.toSet());

        return this.apiaryRepository.findAll().stream()
                .filter(apiary -> apiary.getHiveEntities().stream()
                        .anyMatch(hive -> hive.getProductEntity() != null &&
                                productBarcodes.contains(hive.getProductEntity().getBarcode())))
                .map(ApiaryEntity::getLocation)
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());
    }

    @Override
    public BigDecimal sumProductPricesByRega(String rega) {
        return this.apiaryRepository.findAll().stream()
                .filter(apiary -> rega.equals(apiary.getRega()))
                .flatMap(apiary -> apiary.getHiveEntities().stream())
                .map(HiveEntity::getProductEntity)
                .filter(Objects::nonNull)
                .map(ProductEntity::getPrice)
                .filter(Objects::nonNull)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}