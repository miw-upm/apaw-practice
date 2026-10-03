package es.upm.miw.apaw.domain.model.secondlawchance;

import es.upm.miw.apaw.domain.model.UserSnapshot;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SharedDebtReport {
    private UUID debtId;
    private String contractNumber;
    private String creditorName;
    private BigDecimal amount;
    private CreditorType type;
    private long caseCount;
    private long debtorCount;
    private List<UUID> debtorIds;
    private List<UserSnapshot> debtors;

    public SharedDebtReport(UUID debtId, String contractNumber, String creditorName, BigDecimal amount,
                            CreditorType type, long caseCount, long debtorCount) {
        this.debtId = debtId;
        this.contractNumber = contractNumber;
        this.creditorName = creditorName;
        this.amount = amount;
        this.type = type;
        this.caseCount = caseCount;
        this.debtorCount = debtorCount;
    }
}
