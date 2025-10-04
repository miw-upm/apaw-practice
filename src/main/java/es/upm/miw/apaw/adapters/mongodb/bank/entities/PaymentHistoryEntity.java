package es.upm.miw.apaw.adapters.mongodb.bank.entities;
import lombok.*;
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
}
