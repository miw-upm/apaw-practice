package es.upm.miw.apaw.domain.persistenceports.bank;
import es.upm.miw.apaw.domain.models.bank.PaymentHistory;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PaymentHistoryPersistence {
    PaymentHistory update(UUID id, PaymentHistory paymentHistory);

    PaymentHistory read(UUID id);
}
