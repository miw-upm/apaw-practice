package es.upm.miw.apaw.adapters.mongodb.bank.entities;
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

}

