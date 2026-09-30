package es.upm.miw.apaw.domain.model.evidencemanagement;

import es.upm.miw.apaw.domain.model.UserSnapshot;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
public class CustodyRecord {

    private UUID id;
    private LocalDateTime recordedAt;
    private Integer durationMinutes;
    private String action;
    private String location;
    private String notes;
    private UserSnapshot custodian;

    public CustodyRecord(UUID id, LocalDateTime recordedAt, Integer durationMinutes,
                         String action, String location, String notes,
                         UserSnapshot custodian) {
        this.id = id;
        this.recordedAt = recordedAt;
        this.durationMinutes = durationMinutes;
        this.action = action;
        this.location = location;
        this.notes = notes;
        this.custodian = custodian;
    }
}
