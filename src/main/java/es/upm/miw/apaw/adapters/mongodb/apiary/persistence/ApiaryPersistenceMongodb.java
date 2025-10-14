package es.upm.miw.apaw.adapters.mongodb.apiary.persistence;

import es.upm.miw.apaw.adapters.mongodb.apiary.daos.ApiaryRepository;
import es.upm.miw.apaw.adapters.mongodb.apiary.daos.SaleRepository;
import es.upm.miw.apaw.adapters.mongodb.apiary.entities.ApiaryEntity;
import es.upm.miw.apaw.adapters.mongodb.apiary.entities.SaleEntity;
import es.upm.miw.apaw.domain.models.apiary.Apiary;
import es.upm.miw.apaw.domain.models.apiary.Hive;
import es.upm.miw.apaw.domain.models.apiary.Product;
import es.upm.miw.apaw.domain.persistenceports.apiary.ApiaryPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
                .map(product -> product.getBarcode())
                .collect(Collectors.toSet());

        return this.apiaryRepository.findAll().stream()
                .filter(apiary -> apiary.getHiveEntities().stream()
                        .anyMatch(hive -> hive.getProductEntity() != null &&
                                productBarcodes.contains(hive.getProductEntity().getBarcode())))
                .map(ApiaryEntity::getLocation)
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());
    }
}
