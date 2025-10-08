package es.upm.miw.apaw.adapters.resources.bank;
import es.upm.miw.apaw.domain.models.bank.PaymentHistoryUpdating;
import es.upm.miw.apaw.domain.services.bank.PaymentHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(PaymentHistoryResource.PAYMENT_HISTORY)
public class PaymentHistoryResource {
    public static final String PAYMENT_HISTORY = "/paymentHistory/payment-histories";
    public final PaymentHistoryService paymentHistoryService;

    @Autowired
    public PaymentHistoryResource(PaymentHistoryService paymentHistoryService){
        this.paymentHistoryService=paymentHistoryService;
    }

    @PatchMapping
    public void updatePrices(@RequestBody List<PaymentHistoryUpdating> paymentHistoryUpdatingList) {
        this.paymentHistoryService.updatePaymentHistoryPaid(paymentHistoryUpdatingList.stream());
    }
}
