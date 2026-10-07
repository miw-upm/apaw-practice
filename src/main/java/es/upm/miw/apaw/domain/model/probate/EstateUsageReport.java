package es.upm.miw.apaw.domain.model.probate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EstateUsageReport {
    private HeirStatus heirStatus;
    private long heirCount;
    private BigDecimal totalSharePercentage;
}
