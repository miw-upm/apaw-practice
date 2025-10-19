package es.upm.miw.apaw.domain.models.metro;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SumTicketPrices {
    private BigDecimal sumPrices;
}
