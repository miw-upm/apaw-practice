package es.upm.miw.apaw.domain.services.bank;

import es.upm.miw.apaw.domain.models.bank.PaymentHistory;
import es.upm.miw.apaw.domain.models.bank.PaymentHistoryUpdating;
import es.upm.miw.apaw.domain.models.shop.Article;
import es.upm.miw.apaw.domain.persistenceports.bank.PaymentHistoryPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Stream;

@Service
public class PaymentHistoryService {
    private final PaymentHistoryPersistence paymentHistoryPersistence;

    @Autowired
    public PaymentHistoryService(PaymentHistoryPersistence paymentHistoryPersistence){
        this.paymentHistoryPersistence = paymentHistoryPersistence;
    }

    public void updatePaymentHistoryPaid(Stream<PaymentHistoryUpdating> paymentHistoryUpdatingList) {
        paymentHistoryUpdatingList.map(paymentHistoryUpdating -> {
                    PaymentHistory paymentHistory
                            = this.paymentHistoryPersistence.read(paymentHistoryUpdating.getId());
                    paymentHistory.setPaid(paymentHistoryUpdating.getPaid());
                    return paymentHistory;
                })
                .forEach(paymentHistory -> this.paymentHistoryPersistence.update(paymentHistory.getId(), paymentHistory));
    }
}
