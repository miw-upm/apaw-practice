package es.upm.miw.apaw.adapters.mongodb.bank.entities;
import es.upm.miw.apaw.domain.models.bank.CreditCard;
import es.upm.miw.apaw.domain.models.bank.PaymentHistory;
import lombok.*;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class CreditCardEntity {

    @EqualsAndHashCode.Include
    @Indexed(unique = true)
    private String cardNumber;
    private LocalDate expirationDate;
    private Integer cvv;
    private BigDecimal cardLimit;
    @DBRef
    private List<PaymentHistoryEntity> paymentHistoryList;

    public CreditCard toCreditCard(){

        List<PaymentHistory> paymentHistories = this.paymentHistoryList
                .stream()
                .map(PaymentHistoryEntity::toPaymentHistory)
                .toList();

        return new CreditCard(cardNumber,expirationDate,cvv,cardLimit,paymentHistories);
    }

}

