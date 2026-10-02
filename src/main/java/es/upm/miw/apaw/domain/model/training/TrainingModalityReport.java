package es.upm.miw.apaw.domain.model.training;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TrainingModalityReport {
    private Boolean online;
    private Integer planCount;
    private Integer totalDurationHours; 
}