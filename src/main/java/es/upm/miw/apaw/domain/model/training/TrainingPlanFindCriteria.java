package es.upm.miw.apaw.domain.model.training;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TrainingPlanFindCriteria {

    private BigDecimal evaluationScore;
    
    private String courseName;
    
    private String userFirstName;

    public boolean isAll() {
        return !this.hasEvaluationScore()
                && !this.hasCourseName() && !this.hasUserFirstName();
    }

    public boolean hasEvaluationScore() {
        return this.evaluationScore != null;
    }

    public boolean hasCourseName() {
        return this.courseName != null && !this.courseName.isBlank();
    }
    public boolean hasUserFirstName() {
        return this.userFirstName != null && !this.userFirstName.isBlank();
    }
}
