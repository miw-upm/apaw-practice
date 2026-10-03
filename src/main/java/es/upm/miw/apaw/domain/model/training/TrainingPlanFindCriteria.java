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
    
    private Boolean isCompleted;
    
    private String courseName;
    
    private Boolean userActive;

    public boolean isAll() {
        return !this.hasEvaluationScore() && !this.hasIsCompleted()
                && !this.hasCourseName() && !this.hasUserActive();
    }

    public boolean hasEvaluationScore() {
        return this.evaluationScore != null;
    }

    public boolean hasIsCompleted() {
        return this.isCompleted != null;
    }

    public boolean hasCourseName() {
        return this.courseName != null && !this.courseName.isBlank();
    }
    public boolean hasUserActive() {
        return this.userActive != null;
    }
}