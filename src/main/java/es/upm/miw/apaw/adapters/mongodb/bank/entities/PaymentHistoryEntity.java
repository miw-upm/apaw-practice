package es.upm.miw.apaw.adapters.mongodb.bank.entities;
import es.upm.miw.apaw.domain.models.bank.PaymentHistory;
import lombok.*;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Document
public class PaymentHistoryEntity {

    @EqualsAndHashCode.Include
    @Id
    private UUID id;
    private BigDecimal amount;
    private LocalDateTime paymentDate;
    private Boolean paid;

    public PaymentHistoryEntity(PaymentHistory paymentHistory){
        BeanUtils.copyProperties(paymentHistory, this);
    }

    public PaymentHistory toPaymentHistory(){
        return new PaymentHistory(id,amount,paymentDate,paid);
    }

    public void fromPaymentHistory(PaymentHistory paymentHistory){
        BeanUtils.copyProperties(paymentHistory, this);
    }
}
