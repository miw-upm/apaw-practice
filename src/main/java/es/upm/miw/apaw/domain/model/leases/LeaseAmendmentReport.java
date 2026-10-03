package es.upm.miw.apaw.domain.model.leases;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LeaseAmendmentReport {
    private LeaseType leaseType;
    private long leaseCount;
    private long approvedAmendmentCount;
    private BigDecimal totalAdditionalAmount;
}
