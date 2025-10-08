package es.upm.miw.apaw.adapters.mongodb.bank.persistence;

import es.upm.miw.apaw.adapters.mongodb.bank.daos.PaymentHistoryRepository;
import es.upm.miw.apaw.adapters.mongodb.bank.entities.PaymentHistoryEntity;
import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.models.bank.PaymentHistory;
import es.upm.miw.apaw.domain.models.shop.Article;
import es.upm.miw.apaw.domain.persistenceports.bank.PaymentHistoryPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository("paymentHistoryPersistence")
public class PaymentHistoryPersistenceMongodb implements PaymentHistoryPersistence {

    private final PaymentHistoryRepository paymentHistoryRepository;

    @Autowired
    public PaymentHistoryPersistenceMongodb(PaymentHistoryRepository paymentHistoryRepository){
        this.paymentHistoryRepository = paymentHistoryRepository;
    }

    @Override
    public PaymentHistory update(UUID id, PaymentHistory paymentHistory) {

        PaymentHistoryEntity paymentHistoryEntity = this.paymentHistoryRepository
                .findById(id)
                .orElseThrow(()-> new NotFoundException("Payment history: " + id));
        paymentHistoryEntity.fromPaymentHistory(paymentHistory);
        return this.paymentHistoryRepository.save(paymentHistoryEntity).toPaymentHistory();
    }

    @Override
    public PaymentHistory read(UUID id) {
        return this.paymentHistoryRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException("Payment history id: " + id))
                .toPaymentHistory();
    }
}
