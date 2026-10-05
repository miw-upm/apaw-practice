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
        return new CustodyRecordDto(
                this.durationMinutes, this.action, this.location, this.notes, this.custodianId).toDomain();
    }
}
