package es.upm.miw.apaw.domain.model.contract;

import es.upm.miw.apaw.domain.model.UserSnapshot;

import java.util.UUID;

public record ContractExpirationReport (
    UUID userId,
    UserSnapshot userSnapshot,
    long expiringContractCount,
    long activeClauseCount
) {
    public ContractExpirationReport(
            UUID userId,
            long expiringContractCount,
            long activeClauseCount
    ) {
        this(
                userId,
                null,
                expiringContractCount,
                activeClauseCount
        );
    }
}
