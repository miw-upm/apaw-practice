package es.upm.miw.apaw.adapters.in.evidencemanagement;

import es.upm.miw.apaw.domain.model.UserSnapshot;
import es.upm.miw.apaw.domain.model.evidencemanagement.CustodyRecord;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Pattern;

import java.util.UUID;

public record CustodyRecordPatchDto(
        Integer durationMinutes,
        @Schema(example = "Transfer to laboratory")
        @Pattern(regexp = ".*\\S.*", message = "must not be blank") String action,
        String location,
        String notes,
        UUID custodianId) {

    public CustodyRecord toDomain() {
        return CustodyRecord.builder()
                .durationMinutes(this.durationMinutes)
                .action(this.action)
                .location(this.location)
                .notes(this.notes)
                .custodian(this.custodianId == null ? null : UserSnapshot.builder().id(this.custodianId).build())
                .build();
    }
}