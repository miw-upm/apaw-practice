package es.upm.miw.apaw.adapters.mongodb.bank.entities;
import es.upm.miw.apaw.domain.models.bank.Loan;
import lombok.*;
import org.springframework.beans.BeanUtils;
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

    public LoanEntity (Loan loan){
        BeanUtils.copyProperties(loan, this);
        this.id = UUID.randomUUID();
    }

    public Loan toLoan(){
        return new Loan(id,quantity,interestRate,condition);
    }
}
