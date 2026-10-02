package es.upm.miw.apaw.domain.model.training;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TrainingModalityReport {
    private Boolean online;
    private Long planCount;
    private Long totalDurationHours; 
}