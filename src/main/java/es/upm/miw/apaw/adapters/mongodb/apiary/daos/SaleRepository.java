package es.upm.miw.apaw.adapters.mongodb.apiary.daos;

import es.upm.miw.apaw.adapters.mongodb.apiary.entities.SaleEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;
import java.util.List;

import java.util.UUID;

public interface SaleRepository extends MongoRepository<SaleEntity, UUID> {
    Optional<SaleEntity> findByIdSale(int idSale);

    List<SaleEntity> findByPaymentForm(Integer paymentForm);

    List<SaleEntity> findByShippingAddress(String shippingAddress);

    int deleteByIdSale(int idSale);
}