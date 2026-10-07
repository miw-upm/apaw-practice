package es.upm.miw.apaw.domain.model.judicialcourt;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LawyerCourtStat {
    private UUID userId;
    private Long totalCourts;
}
