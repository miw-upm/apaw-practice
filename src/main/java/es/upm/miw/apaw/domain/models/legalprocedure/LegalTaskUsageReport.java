package es.upm.miw.apaw.domain.models.legalprocedure;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LegalTaskUsageReport {
    private String taskTitle;
    private long totalUsageCount;
    private long activeUsageCount;
}
