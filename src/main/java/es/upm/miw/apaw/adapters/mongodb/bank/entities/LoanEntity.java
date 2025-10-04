package es.upm.miw.apaw.adapters.mongodb.bank.entities;
import lombok.*;
import org.springframework.data.annotation.Id;

import java.math.BigDecimal;
import java.util.UUID;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class LoanEntity {

    @EqualsAndHashCode.Include
    @Id
    private UUID id;
    private BigDecimal quantity;
    private Double interestRate;
    private String condition;

}
