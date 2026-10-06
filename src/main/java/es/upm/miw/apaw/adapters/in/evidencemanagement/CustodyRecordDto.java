package es.upm.miw.apaw.adapters.in.evidencemanagement;

import es.upm.miw.apaw.domain.model.UserSnapshot;
import es.upm.miw.apaw.domain.model.evidencemanagement.CustodyRecord;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record CustodyRecordDto(
        Integer durationMinutes,
        @Schema(example = "Transfer to laboratory")
        @NotBlank String action,
        String location,
        String notes,
        @NotNull UUID custodianId) {

    public CustodyRecord toDomain() {
        return CustodyRecord.builder()
                .durationMinutes(this.durationMinutes)
                .action(this.action)
                .location(this.location)
                .notes(this.notes)
                .custodian(UserSnapshot.builder().id(this.custodianId).build())
                .build();
    }
}
